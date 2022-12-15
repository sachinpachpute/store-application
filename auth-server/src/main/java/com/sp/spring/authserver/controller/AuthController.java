package com.sp.spring.authserver.controller;

import com.sp.spring.authserver.config.PkceConfigurationProperties;
import com.sp.spring.authserver.constant.ApiPath;
import com.sp.spring.authserver.constant.ApiStatusDescription;
import com.sp.spring.authserver.constant.ApiSummary;
import com.sp.spring.authserver.security.utility.ObjectConverter;
import com.sp.spring.authserver.service.AuthService;
import com.sp.spring.authserver.web.AuthenticationRequest;
import com.sp.spring.authserver.web.AuthenticationResponse;
import com.sp.spring.authserver.web.CodeExchangeRequest;
import com.sp.spring.authserver.web.RefreshTokenRequest;
import com.sp.spring.commons.exception.GenericBadRequestException;
import com.sp.spring.commons.util.RedirectUriBuilder;
import com.sp.spring.commons.util.RequestReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import javax.validation.Valid;

@RestController
@EnableConfigurationProperties(value = PkceConfigurationProperties.class)
public class AuthController {

    @Autowired
    AuthService authService;

    @Autowired
    PkceConfigurationProperties pkceConfigurationProperties;

    @Autowired
    RequestReader requestReader;

    @Autowired
    ObjectConverter objectConverter;

    /*@PostMapping("/authenticate")
    public ResponseEntity<?> userAuthenticationHandler(
            @Valid @RequestBody AuthenticationRequest authenticationRequest) {

        final var response = authService.authenticate(authenticationRequest);
        return ResponseEntity.status(HttpStatus.FOUND).location(RedirectUriBuilder
                .build(authenticationRequest.getRedirectUri(), response.get("code"), response.get("state"))).build();
    }*/

    @PostMapping(value = ApiPath.AUTHENTICATE)
    @ApiResponses(value = { @ApiResponse(responseCode = "302", description = ApiStatusDescription.CODE_RETURNED),
            @ApiResponse(responseCode = "401", description = ApiStatusDescription.INVALID_USER_CREDENTIALS),
            @ApiResponse(responseCode = "400", description = ApiStatusDescription.BAD_REQUEST) })
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = ApiSummary.AUTHENTICATE_REDIRECTION)
    public ResponseEntity<?> userAuthenticationHandler(
            @Valid @RequestBody(required = true) final AuthenticationRequest authenticationRequest) {
        final var response = authService.authenticate(authenticationRequest);
        return ResponseEntity.status(HttpStatus.FOUND).location(RedirectUriBuilder
                .build(authenticationRequest.getRedirectUri(), response.get("code"), response.get("state"))).build();
    }

    @PostMapping(value = ApiPath.TOKEN, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = ApiStatusDescription.SUCCESSFULL_CODE_EXCHANGE),
            @ApiResponse(responseCode = "400", description = ApiStatusDescription.BAD_REQUEST),
            @ApiResponse(responseCode = "403", description = ApiStatusDescription.INVALID_CODE),
            @ApiResponse(responseCode = "412", description = ApiStatusDescription.CODE_VERIFIER_MISMATCH),
            @ApiResponse(responseCode = "401", description = ApiStatusDescription.REFRESH_TOKEN_EXPIRED) })
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(summary = ApiSummary.TOKEN_EXCHANGE)
    public ResponseEntity<AuthenticationResponse> tokenExchangeHandler(
            @Valid @RequestBody(required = true) final String request) {
        final var properties = pkceConfigurationProperties.getSecurity();
        String grantType = requestReader.readGrantType(request);
        if (grantType.equals(properties.getGrantType())) {
            return ResponseEntity
                    .ok(authService.exchangeCode(objectConverter.convert(request, CodeExchangeRequest.class)));
        } else if (grantType.equals(properties.getRefresh().getGrantType())) {
            return ResponseEntity.ok(
                    authService.refreshToken(objectConverter.convertRefresh(request, RefreshTokenRequest.class)));
        }
        throw new GenericBadRequestException("grantType contains unrecognizable value");
    }

    /*@PostMapping("/testEndpoint")
    public ResponseEntity<?> testEndpoint(
            @Valid @RequestBody AuthenticationRequest authenticationRequest) {

        return ResponseEntity.status(HttpStatus.FOUND).build();
    }*/
}
