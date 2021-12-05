package com.maygul.game.rsp.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Action {
  ROCK("com.maygul.game.rsp.enum.action.rock"),
  SCISSOR("com.maygul.game.rsp.enum.action.scissor"),
  PAPER("com.maygul.game.rsp.enum.action.paper");

  private String label;

  //1 = win, 0 = draw, -1 lose
  public static int resultOfClash(Action player, Action app) {
    int result = -1;
    if (player.equals(ROCK)) {
      if (app.equals(SCISSOR)) {
        result = 1;
      } else if (app.equals(ROCK)) {
        result = 0;
      } else if (app.equals(PAPER)) {
        result = -1;
      }
    } else if (player.equals(SCISSOR)) {
      if (app.equals(ROCK)) {
        result = -1;
      } else if (app.equals(SCISSOR)) {
        result = 0;
      } else if (app.equals(PAPER)) {
        result = 1;
      }
    } else if (player.equals(PAPER)) {
      if (app.equals(ROCK)) {
        result = 1;
      } else if (app.equals(SCISSOR)) {
        result = -1;
      } else if (app.equals(PAPER)) {
        result = 0;
      }
    }
    return result;
  }
}