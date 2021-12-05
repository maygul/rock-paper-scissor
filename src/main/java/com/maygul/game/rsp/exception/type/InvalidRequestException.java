package com.maygul.game.rsp.exception.type;


import com.maygul.game.rsp.exception.data.ExceptionData;

/**
 * Http Status 400, Bad Request
 */
public class InvalidRequestException extends MicroException {

  public InvalidRequestException(ExceptionData data) {
    super(data);
  }

  public InvalidRequestException(Long errorCode) {
    super(errorCode);
  }

  public InvalidRequestException(Long errorCode, String message) {
    super(errorCode, message);
  }
}
