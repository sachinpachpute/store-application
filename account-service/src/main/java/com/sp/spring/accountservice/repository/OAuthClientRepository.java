package com.sp.spring.accountservice.repository;

import com.sp.spring.accountservice.repository.dao.OAuthClient;
import org.springframework.data.repository.CrudRepository;

/**
 * @author: Devaraj Reddy, Date : 2019-05-18
 */
public interface OAuthClientRepository extends CrudRepository<OAuthClient, Long> {

}
