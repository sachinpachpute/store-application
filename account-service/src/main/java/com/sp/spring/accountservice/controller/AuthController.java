package com.sp.spring.accountservice.controller;

import com.sp.spring.accountservice.service.AuthService;
import com.sp.spring.accountservice.web.CreateOAuthClientRequest;
import com.sp.spring.accountservice.web.CreateOAuthClientResponse;
import com.sp.spring.accountservice.web.CreateUserResponse;
import com.sp.spring.accountservice.web.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/client")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    public ResponseEntity<CreateOAuthClientResponse> createOAuthClient(
            @Valid @RequestBody CreateOAuthClientRequest createOAuthClientRequest) {

        CreateOAuthClientResponse oAuthClient = authService.createOAuthClient(createOAuthClientRequest);
        return new ResponseEntity<>(oAuthClient, HttpStatus.CREATED);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        System.out.println("registerUser ******************************************");
        System.out.println("registerUser ******************************************");

        CreateUserResponse createUserResponse = authService.registerUser(signUpRequest);

        return new ResponseEntity<>(createUserResponse, HttpStatus.CREATED);
    }
}
