package com.sp.spring.accountservice.service;

import com.sp.spring.accountservice.web.*;
import com.sp.spring.accountservice.web.*;

import java.util.List;

/**
 * @author: Devaraj Reddy, Date : 2019-09-27
 */
public interface UserService {

  String createUser(CreateUserRequest createUserRequest);

  GetUserResponse getUserByUserName(String userName);

  GetUserResponse getUserByUserId(String userId);

  GetUserInfoResponse getUserInfo();

  void updateUserInfo(UpdateUserRequest updateUserRequest);

  void deleteUserById(String userId);

  List<GetUserResponse> getAllUsers();

  void updateUser(String userId, UpdateUserRequestFromAdmin updateUserRequestFromAdmin);
}
