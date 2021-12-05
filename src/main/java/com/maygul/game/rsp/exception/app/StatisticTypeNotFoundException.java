package com.maygul.game.rsp.exception.app;

import com.maygul.game.rsp.exception.constant.RspErrorType;
import com.maygul.game.rsp.exception.type.DataNotFoundException;

public class StatisticTypeNotFoundException extends DataNotFoundException {
  public StatisticTypeNotFoundException() {
    super(RspErrorType.STATISTIC_TYPE_NOT_FOUND.getCode(),
        RspErrorType.STATISTIC_TYPE_NOT_FOUND.getLabel());
  }
}
