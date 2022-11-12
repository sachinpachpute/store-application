package com.sp.spring.accountservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: Devaraj Reddy, Date : 2019-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOAuthClientRequest {

  private List<String> authorized_grant_types;
  private List<String> authorities;
  private List<String> scope;
  private List<String> resource_ids;

}
