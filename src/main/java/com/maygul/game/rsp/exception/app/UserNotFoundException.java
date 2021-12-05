package com.maygul.game.rsp.exception.app;

import com.maygul.game.rsp.exception.constant.RspErrorType;
import com.maygul.game.rsp.exception.type.DataNotFoundException;

public class UserNotFoundException extends DataNotFoundException {
  public UserNotFoundException() {
    super(RspErrorType.USER_NOT_FOUND.getCode(), RspErrorType.USER_NOT_FOUND.getLabel());
  }
}
