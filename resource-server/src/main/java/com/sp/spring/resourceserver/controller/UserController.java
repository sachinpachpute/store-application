package com.sp.spring.resourceserver.controller;

import com.sp.spring.resourceserver.service.UserService;
import com.sp.spring.resourceserver.web.GetUserInfoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  @Autowired
  private UserService userService;


  @GetMapping("/userInfo")
  public ResponseEntity<GetUserInfoResponse> getUserInfo() {
    GetUserInfoResponse userInfo = userService.getUserInfo();
    return new ResponseEntity<>(userInfo, HttpStatus.OK);
  }


}
