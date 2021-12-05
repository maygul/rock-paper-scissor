package com.maygul.game.rsp.exception.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RspErrorType {
  ACTION_NOT_FOUND(-1L, "com.maygul.game.rsp.exception.action.not.found"),
  STATISTIC_TYPE_NOT_FOUND(-2L, "com.maygul.game.rsp.exception.statistic-type.not.found"),
  USER_NOT_FOUND(-3L, "com.maygul.game.rsp.exception.user.not.found"),
  USERNAME_ALREADY_EXIST(-4L, "com.maygul.game.rsp.exception.username.already.exist");

  private Long code;
  private String label;
}
