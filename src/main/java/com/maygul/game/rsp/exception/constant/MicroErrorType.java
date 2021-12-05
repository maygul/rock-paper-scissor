package com.maygul.game.rsp.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum MicroErrorType {

  INVALID_REQUEST(-1L, "invalid request"),
  HANDLER_NOT_FOUND(-2L, "handler not found"),
  METHOD_NOT_ALLOWED(-3L, "http method not allowed"),
  VALIDATION_ERROR(-4L, "validation error"),
  INTERNAL_ERROR(-5L, "internal error");

  private final Long code;
  private final String message;

}
