package com.maygul.game.rsp.exception.type;


import com.maygul.game.rsp.exception.data.ExceptionData;

/**
 * Http Status 405, Method Not Allowed
 */
public class MethodNotAllowedException extends MicroException {
  public MethodNotAllowedException(ExceptionData data) {
    super(data);
  }

  public MethodNotAllowedException(Long errorCode) {
    super(errorCode);
  }

  public MethodNotAllowedException(Long errorCode, String message) {
    super(errorCode, message);
  }
}
