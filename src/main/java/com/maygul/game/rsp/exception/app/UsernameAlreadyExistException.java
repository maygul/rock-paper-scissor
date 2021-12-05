package com.maygul.game.rsp.exception.app;

import com.maygul.game.rsp.exception.constant.RspErrorType;
import com.maygul.game.rsp.exception.type.DataConflictException;

public class UsernameAlreadyExistException extends DataConflictException {
  public UsernameAlreadyExistException() {
    super(RspErrorType.USERNAME_ALREADY_EXIST.getCode(),
        RspErrorType.USERNAME_ALREADY_EXIST.getLabel());
  }
}
