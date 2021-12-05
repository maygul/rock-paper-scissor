package com.maygul.game.rsp.exception.type;

import com.maygul.game.rsp.exception.data.ExceptionData;
import java.util.Map;

/**
 * Http Status 409, Conflict
 */
public class DataConflictException extends MicroException {

  public DataConflictException(ExceptionData data) {
    super(data);
  }

  public DataConflictException(ExceptionData data, Map<String, String> parameters) {
    super(data, parameters);
  }

  public DataConflictException(Long errorCode) {
    super(errorCode);
  }

  public DataConflictException(Long errorCode, String message) {
    super(errorCode, message);
  }
}
