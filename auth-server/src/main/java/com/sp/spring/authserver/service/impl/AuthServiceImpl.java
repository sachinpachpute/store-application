package com.sp.spring.authserver.service.impl;

import com.google.common.cache.LoadingCache;
import com.sp.spring.authserver.config.PkceConfigurationProperties;
import com.sp.spring.authserver.config.PkceConfigurationProperties.Security;
import com.sp.spring.authserver.repository.dao.User;
import com.sp.spring.authserver.repository.UserRepository;
import com.sp.spring.authserver.security.utility.JwtUtils;
import com.sp.spring.authserver.service.AuthService;
import com.sp.spring.authserver.web.*;
import com.sp.spring.commons.exception.*;
import com.sp.spring.commons.exception.InvalidCredentialsException;
import com.sp.spring.commons.util.CodeUtility;
import com.sp.spring.commons.util.RequestReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@EnableConfigurationProperties(value = PkceConfigurationProperties.class)
public class AuthServiceImpl implements AuthService {

  @Autowired
  BCryptPasswordEncoder passwordEncoder;

  @Autowired
  UserRepository userRepository;

  @Autowired
  PkceConfigurationProperties pkceConfigurationProperties;

  @Autowired
  JwtUtils jwtUtils;

  @Autowired
  RequestReader requestReader;

  @Autowired
  LoadingCache<String, UserAuthentication> codeCache;


  @Override
  public Map<String, String> authenticate(AuthenticationRequest authenticationRequest) {
    Optional<User> userOptional = userRepository
            .findByUserNameOrEmail(authenticationRequest.getUserName(), authenticationRequest.getUserName());

    User user = userOptional.orElseThrow(() ->
            new InvalidCredentialsException());

    if (!passwordEncoder.matches(authenticationRequest.getPassword(), user.getPassword()))
      throw new InvalidCredentialsException();

    final var securityProperties = pkceConfigurationProperties.getSecurity();
    validateAuthenticationRequest(authenticationRequest, securityProperties);

    final String generatedCode = CodeUtility.generateCode();
    codeCache.put(generatedCode,
            UserAuthentication.builder().userId(user.getUserId()).authentication(authenticationRequest).build());
    final var response = new HashMap<String, String>();
    response.put("code", generatedCode);
    response.put("state", authenticationRequest.getState());
    return response;
  }

  @Override
  public AuthenticationResponse exchangeCode(final CodeExchangeRequest codeExchangeRequest) {
    final var securityProperties = pkceConfigurationProperties.getSecurity();

    UserAuthentication userAuthentication;
    try {
      if (codeCache.get(codeExchangeRequest.getCode()).getAuthentication() != null)
        userAuthentication = codeCache.get(codeExchangeRequest.getCode());
      else
        throw new InvalidCodeException();
    } catch (ExecutionException e) {
      log.error("Unable to fetch code: ", e);
      throw new InvalidCodeException();
    }
    validateCodeExchangeRequest(codeExchangeRequest, securityProperties,
            userAuthentication.getAuthentication());
    codeCache.invalidate(codeExchangeRequest.getCode());

    final var generatedCodeChallenge = CodeUtility.codeChallengeGenerator()
            .generate(codeExchangeRequest.getCodeVerifier());

    if (!generatedCodeChallenge.equals(userAuthentication.getAuthentication().getCodeChallenge()))
      throw new CodeChallengeAndVerifierMismatchException();

    final var user = userRepository.findByUserId(userAuthentication.getUserId()).get();
    return AuthenticationResponse.builder().accessToken(jwtUtils.generateAccessToken(user))
            .refreshToken(jwtUtils.generateRefreshToken(user)).tokenType("Bearer")
            .expiresIn(TimeUnit.HOURS.toSeconds(1)).build();
  }

  @Override
  public AuthenticationResponse refreshToken(final RefreshTokenRequest refreshTokenRequest) {
    if (!refreshTokenRequest.getClientId().equals(pkceConfigurationProperties.getSecurity().getClientId()))
      throw new GenericBadRequestException("Invalid client-id");

    if (jwtUtils.isTokenExpired(refreshTokenRequest.getRefreshToken()))
      throw new GenericUnauthorizedExcpetion();
    final var user = userRepository.findByEmail(jwtUtils.extractEmail(refreshTokenRequest.getRefreshToken()))
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST));
    return AuthenticationResponse.builder().accessToken(jwtUtils.generateAccessToken(user))
            .refreshToken(refreshTokenRequest.getRefreshToken()).tokenType("Bearer")
            .expiresIn(TimeUnit.HOURS.toSeconds(1)).build();
  }

  private void validateAuthenticationRequest(AuthenticationRequest authenticationRequest, PkceConfigurationProperties.Security securityProperties) {
    if (!authenticationRequest.getClientId().equals(securityProperties.getClientId()))
      throw new GenericBadRequestException("Invalid client-id");
    if (!authenticationRequest.getResponseType().equals(securityProperties.getResponseType()))
      throw new GenericBadRequestException("Invalid response-type value");
    if (!requestReader.isValidRedirectUri(authenticationRequest.getRedirectUri(),
            securityProperties.getRedirectUri()))
      throw new GenericBadRequestException("Invalid redirect-uri");
    if (!authenticationRequest.getCodeChallengeMethod().equals(securityProperties.getCodeChallengeMethod()))
      throw new GenericBadRequestException("Invalid code-challenge-method");
  }

  private void validateCodeExchangeRequest(final CodeExchangeRequest codeExchangeRequest,
                                           final Security securityProperties, final AuthenticationRequest authenticationRequest) {
    if (!codeExchangeRequest.getClientId().equals(authenticationRequest.getClientId()))
      throw new GenericBadRequestException("Invalid client-id");
    if (!codeExchangeRequest.getGrantType().equals(securityProperties.getGrantType()))
      throw new GenericBadRequestException("Invalid grant-type value");
    if (!codeExchangeRequest.getRedirectUri().equals(authenticationRequest.getRedirectUri()))
      throw new GenericBadRequestException("Invalid redirect-uri");
  }
}
