package com.sp.spring.accountservice.exception;

import com.sp.spring.commons.exception.ErrorResponse;
import lombok.Getter;

/**
 * @author: Devaraj Reddy, Date : 2019-06-30
 */
public class SuccessCodeWithErrorResponse extends RuntimeException {

  @Getter
  private ErrorResponse errorResponse;

  @Getter
  private String id;

  public SuccessCodeWithErrorResponse(String id, ErrorResponse errorResponse) {
    this.id = id;
    this.errorResponse = errorResponse;
  }

  public SuccessCodeWithErrorResponse(ErrorResponse errorResponse) {
    this.errorResponse = errorResponse;
  }

}
