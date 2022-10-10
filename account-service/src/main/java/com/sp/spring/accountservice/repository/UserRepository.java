package com.sp.spring.accountservice.repository;



import com.sp.spring.accountservice.entity.User;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface  UserRepository extends CrudRepository<User, Long> {

  Optional<User> findByUserName(String username);

  Optional<User> findByUserNameOrEmail(String uName, String eMail);

  Optional<User> findByUserId(String userId);

  void deleteByUserId(String userId);

  Boolean existsByUserName(String userName);

  Boolean existsByEmail(String email);

}
