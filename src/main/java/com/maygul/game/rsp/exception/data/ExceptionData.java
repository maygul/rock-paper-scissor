package com.maygul.game.rsp.exception.data;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExceptionData implements Serializable {

  private String applicationName;
  private Long errorCode;
  private String errorMessage;
  private Boolean showExceptionDetails;

  public ExceptionData() {
  }

  public ExceptionData(Long errorCode) {
    this.errorCode = errorCode;
  }

  public ExceptionData(Long errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("[").
        append("applicationName:").
        append(applicationName).
        append(",").
        append("errorCode:").
        append(errorCode).
        append(",").
        append("errorMessage:").
        append(errorMessage).
        append("]");
    return builder.toString();
  }
}
