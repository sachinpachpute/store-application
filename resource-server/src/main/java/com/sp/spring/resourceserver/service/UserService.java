package com.sp.spring.resourceserver.service;

import com.sp.spring.resourceserver.web.GetUserInfoResponse;
import com.sp.spring.resourceserver.web.GetUserResponse;

import java.util.List;

public interface UserService {

  GetUserResponse getUserByUserName(String userName);

  GetUserInfoResponse getUserInfo();

}
