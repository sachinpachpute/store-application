package com.sp.spring.authserver.web;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author: Devaraj Reddy, Date : 2019-05-20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  private String userId;

  @NotBlank
  @Size(max = 40, message = "UserName length should not be grater than 40 characters")
  private String userName;

  @NotBlank
  @Size(min = 6, max = 20, message = "password length should not be between 6 and 20 characters")
  private String password;

  @NotBlank(message = "responseType must not be empty")
  private String clientId;

  @NotBlank(message = "responseType must not be empty")
  private String responseType;

  @NotBlank(message = "redirectUri must not be empty")
  private String redirectUri;

  @NotBlank(message = "codeChallengeMethod must not be empty")
  private String codeChallengeMethod;

  @NotBlank(message = "codeChallenge must not be empty")
  private String codeChallenge;

  private String state;

}
