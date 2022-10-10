package com.sp.spring.accountservice.repository;

import com.sp.spring.accountservice.entity.Role;
import org.springframework.data.repository.CrudRepository;
import com.sp.spring.accountservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {

  Optional<Role> findByRoleName(String name);

  Boolean existsByRoleName(String roleName);

  @Override
  List<Role> findAll();
}
