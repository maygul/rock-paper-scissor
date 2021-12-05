package com.maygul.game.rsp.exception.app;

import com.maygul.game.rsp.exception.constant.RspErrorType;
import com.maygul.game.rsp.exception.type.DataNotFoundException;

public class ActionNotFoundException extends DataNotFoundException {
  public ActionNotFoundException() {
    super(RspErrorType.ACTION_NOT_FOUND.getCode(), RspErrorType.ACTION_NOT_FOUND.getLabel());
  }
}
