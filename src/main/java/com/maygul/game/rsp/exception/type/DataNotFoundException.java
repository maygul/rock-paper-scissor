package com.maygul.game.rsp.exception.type;



import com.maygul.game.rsp.exception.data.ExceptionData;
import java.util.Map;

/**
 * Http Status 404, Not Found
 */
public class DataNotFoundException extends MicroException {

  public DataNotFoundException(ExceptionData data) {
    super(data);
  }

  public DataNotFoundException(ExceptionData data, Map<String, String> parameters) {
    super(data, parameters);
  }

  public DataNotFoundException(Long errorCode) {
    super(errorCode);
  }

  public DataNotFoundException(Long errorCode, String message) {
    super(errorCode, message);
  }
}
