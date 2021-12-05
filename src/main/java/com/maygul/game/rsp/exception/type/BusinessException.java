package com.maygul.game.rsp.exception.type;

import com.maygul.game.rsp.exception.data.ExceptionData;
import java.util.Map;

/**
 * Http Status 500, Internal Server Error
 */
public class BusinessException extends MicroException {

  public BusinessException(ExceptionData data) {
    super(data);
  }

  public BusinessException(ExceptionData data, Map<String, String> parameters) {
    super(data, parameters);
  }

  public BusinessException(Long errorCode) {
    super(errorCode);
  }

  public BusinessException(Long errorCode, String message) {
    super(errorCode, message);
  }
}
