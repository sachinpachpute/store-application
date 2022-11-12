package com.sp.spring.accountservice.service.impl;

import com.sp.spring.accountservice.exception.SuccessCodeWithErrorResponse;
import com.sp.spring.accountservice.repository.RoleRepository;
import com.sp.spring.accountservice.repository.UserRepository;
import com.sp.spring.accountservice.repository.dao.Role;
import com.sp.spring.accountservice.repository.dao.User;
import com.sp.spring.accountservice.service.UserRoleService;
import com.sp.spring.accountservice.web.MapRoleToUsersRequest;
import com.sp.spring.accountservice.web.MapUserToRolesRequest;
import com.sp.spring.commons.exception.ErrorResponse;
import com.sp.spring.commons.exception.Error;
import com.sp.spring.commons.exception.RunTimeExceptionPlaceHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * @author: Devaraj Reddy, Date : 2019-07-01
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Override
  public void mapUserToRoles(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest) {

    Optional<User> userNameOrEmailOptional = userRepository
        .findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

    User user = userNameOrEmailOptional.orElseThrow(() ->
        new RunTimeExceptionPlaceHolder("UserNameOrEmail doesn't exist!!")
    );

    Set<Role> roles = user.getRoles();

    ErrorResponse errorResponse = ErrorResponse.builder()
        .uuid(UUID.randomUUID())
        .errors(new ArrayList<>())
        .build();

    mapUserToRolesRequest.getRoleNames().forEach(roleName -> {
      //if role exists add to list and persist, else add to error response persist valid roles and send
      // response containing invalid roles.
      roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> roles.add(role))
          .orElse(() -> {
            Error error = Error.builder()
                .code("400")
                .message(roleName + " role doesn't exist!!")
                .build();
            errorResponse.getErrors().add(error);
          })
          .run();
    });

    user.setRoles(roles);

    userRepository.save(user);

    if (!errorResponse.getErrors().isEmpty()) {
      throw new SuccessCodeWithErrorResponse(errorResponse);
    }

  }

  @Override
  public void removeRolesFromUser(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest) {

    Optional<User> userNameOrEmailOptional = userRepository
            .findByUserNameOrEmail(userNameOrEmail, userNameOrEmail);

    User user = userNameOrEmailOptional.orElseThrow(() ->
            new RunTimeExceptionPlaceHolder("UserNameOrEmail doesn't exist!!")
    );

    Set<Role> roles = user.getRoles();

    ErrorResponse errorResponse = ErrorResponse.builder()
            .uuid(UUID.randomUUID())
            .errors(new ArrayList<>())
            .build();

    mapUserToRolesRequest.getRoleNames().forEach(roleName -> {
      //if role exists add to list and persist, else add to error response persist valid roles and send
      // response containing invalid roles.
      roleRepository.findByRoleName(roleName).<Runnable>map(role -> () -> roles.remove(role))
              .orElse(() -> {
                Error error = Error.builder()
                        .code("400")
                        .message(roleName + " role doesn't exist!!")
                        .build();
                errorResponse.getErrors().add(error);
              })
              .run();
    });

    user.setRoles(roles);

    userRepository.save(user);

    if (!errorResponse.getErrors().isEmpty()) {
      throw new SuccessCodeWithErrorResponse(errorResponse);
    }
  }

  @Override
  public void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest) {

    Role role = roleRepository.findByRoleName(roleName)
        .orElseThrow(() -> new RuntimeException("Role doesn't exist!!"));

    ErrorResponse errorResponse = ErrorResponse.builder()
        .uuid(UUID.randomUUID())
        .errors(new ArrayList<>())
        .build();

    mapRoleToUsersRequest.getUserNames().forEach(userName -> {
      userRepository.findByUserName(userName).<Runnable>map(user -> () -> role.addUser(user))
          .orElse(() -> {
            Error error = Error.builder()
                .code("400")
                .message(userName + " userName doesn't exist!!")
                .build();
            errorResponse.getErrors().add(error);
          })
          .run();
    });

    roleRepository.save(role);

    if (!errorResponse.getErrors().isEmpty()) {
      throw new SuccessCodeWithErrorResponse(errorResponse);
    }
  }
}
