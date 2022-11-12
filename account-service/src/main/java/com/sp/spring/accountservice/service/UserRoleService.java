package com.sp.spring.accountservice.service;

import com.sp.spring.accountservice.web.MapRoleToUsersRequest;
import com.sp.spring.accountservice.web.MapUserToRolesRequest;

/**
 * @author: Devaraj Reddy, Date : 2019-09-27
 */
public interface UserRoleService {

  void mapUserToRoles(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest);

  void removeRolesFromUser(String userNameOrEmail, MapUserToRolesRequest mapUserToRolesRequest);

  void mapRoleToUsers(String roleName, MapRoleToUsersRequest mapRoleToUsersRequest);
}
