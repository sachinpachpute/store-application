package com.sp.spring.authserver.web;

import java.util.UUID;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Builder
@Jacksonized
public class UserAuthentication {

    private final String userId;
    private final AuthenticationRequest authentication;

}
