package com.sp.spring.accountservice.service.impl;

import com.sp.spring.accountservice.entity.Role;
import com.sp.spring.accountservice.repository.RoleRepository;
import com.sp.spring.accountservice.repository.UserRepository;
import com.sp.spring.accountservice.service.AuthService;
import com.sp.spring.accountservice.web.CreateUserResponse;
import com.sp.spring.accountservice.web.SignUpRequest;
import com.sp.spring.commons.exception.RunTimeExceptionPlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class AuthSerivceImpl implements AuthService {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public CreateUserResponse registerUser(SignUpRequest signUpRequest) {

        if (userRepository.existsByUserName(signUpRequest.getUserName())) {
            throw new RunTimeExceptionPlaceHolder("Username is already taken!!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new RunTimeExceptionPlaceHolder("Email address already in use!!");
        }

        // Creating user's account
        com.sp.spring.accountservice.entity.User user =
                new com.sp.spring.accountservice.entity.User(
                        signUpRequest.getUserName(),
                        signUpRequest.getPassword(),
                        signUpRequest.getFirstName(),
                        signUpRequest.getLastName(),
                        signUpRequest.getEmail());

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByRoleName("STANDARD_USER")
                .orElseThrow(() -> new RuntimeException("User Role not set."));

        user.setRoles(Collections.singleton(userRole));

        com.sp.spring.accountservice.entity.User savedUser =
                userRepository.save(user);

        return CreateUserResponse.builder()
                .userId(savedUser.getUserId())
                .userName(savedUser.getUserName())
                .build();

    }

}
