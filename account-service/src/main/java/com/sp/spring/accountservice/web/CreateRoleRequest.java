package com.sp.spring.accountservice.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author: Devaraj Reddy, Date : 2019-06-30
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateRoleRequest {

  @NotBlank
  private String roleName;
  @NotBlank
  private String roleDescription;

}
