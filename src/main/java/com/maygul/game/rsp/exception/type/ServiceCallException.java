package com.maygul.game.rsp.exception.type;


import com.maygul.game.rsp.exception.data.ExceptionData;

/**
 * Http Status 503, Service Unavailable
 */
public class ServiceCallException extends MicroException {
  public ServiceCallException(ExceptionData data) {
    super(data);
  }
}
