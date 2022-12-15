package com.sp.spring.authserver.service;

import com.sp.spring.authserver.web.AuthenticationRequest;
import com.sp.spring.authserver.web.AuthenticationResponse;
import com.sp.spring.authserver.web.CodeExchangeRequest;
import com.sp.spring.authserver.web.RefreshTokenRequest;

import java.util.Map;

public interface AuthService {

    //CreateOAuthClientResponse createOAuthClient(CreateOAuthClientRequest createOAuthClientRequest);
    //CreateUserResponse registerUser(SignUpRequest signUpRequest);

    //void create(final UserCreationRequestDto userCreationRequestDto);

    Map<String, String> authenticate(final AuthenticationRequest authenticationRequest);

    AuthenticationResponse exchangeCode(final CodeExchangeRequest codeExchangeRequest);

    AuthenticationResponse refreshToken(final RefreshTokenRequest refreshTokenRequest);
}
