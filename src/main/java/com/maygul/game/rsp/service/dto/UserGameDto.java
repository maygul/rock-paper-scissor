package com.maygul.game.rsp.service.dto;

import com.maygul.game.rsp.persistence.entity.Action;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserGameDto {
  private Action userAction;
  private String userActionDescription;
  private Action appAction;
  private String appActionDescription;
  private int score;
}
