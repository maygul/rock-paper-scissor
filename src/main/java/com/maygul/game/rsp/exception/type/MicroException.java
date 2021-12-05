package com.maygul.game.rsp.exception.type;

import com.maygul.game.rsp.exception.data.ExceptionData;
import java.util.HashMap;
import java.util.Map;

public class MicroException extends Exception {

  private ExceptionData data;

  private Map<String, String> parameters = new HashMap<>();

  public MicroException(ExceptionData data) {
    super(data.getErrorMessage());
    this.data = data;
  }

  public MicroException(ExceptionData data, Exception ex) {
    super(data.getErrorMessage(), ex);
    this.data = data;
  }

  public MicroException(ExceptionData data, Map<String, String> parameters) {
    this(data);
    this.parameters = parameters;
  }

  public MicroException(Long errorCode) {
    this(new ExceptionData(errorCode));
  }

  public MicroException(Long errorCode, String message) {
    this(new ExceptionData(errorCode, message));
  }

  public ExceptionData getData() {
    return data;
  }

}
