package com.sp.spring.accountservice.service;

import com.sp.spring.accountservice.web.CreateUserResponse;
import com.sp.spring.accountservice.web.SignUpRequest;

public interface AuthService {

    CreateUserResponse registerUser(SignUpRequest signUpRequest);
}
