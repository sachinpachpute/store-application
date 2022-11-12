package com.sp.spring.accountservice.service;

import com.sp.spring.accountservice.repository.dao.Role;
import com.sp.spring.accountservice.web.CreateRoleRequest;

import java.util.List;

/**
 * @author: Devaraj Reddy, Date : 2019-09-27
 */
public interface RoleService {

  String createRole(CreateRoleRequest createRoleRequest);

  List<Role> getAllRoles();
}
