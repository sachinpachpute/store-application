package com.sp.spring.accountservice.service;

import com.sp.spring.accountservice.web.CreateOAuthClientRequest;
import com.sp.spring.accountservice.web.CreateOAuthClientResponse;
import com.sp.spring.accountservice.web.CreateUserResponse;
import com.sp.spring.accountservice.web.SignUpRequest;

public interface AuthService {

    CreateOAuthClientResponse createOAuthClient(CreateOAuthClientRequest createOAuthClientRequest);
    CreateUserResponse registerUser(SignUpRequest signUpRequest);
}
