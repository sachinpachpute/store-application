package com.sp.spring.authserver.web;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class RefreshTokenRequest {

    private final String grantType;
    private final String refreshToken;
    private final String clientId;

}
