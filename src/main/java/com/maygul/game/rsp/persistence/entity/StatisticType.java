package com.maygul.game.rsp.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum StatisticType {
  HOURLY("com.maygul.game.rsp.enum.statistic-type.hourly"),
  DAILY("com.maygul.game.rsp.enum.statistic-type.daily"),
  WEEKLY("com.maygul.game.rsp.enum.statistic-type.weekly"),
  MONTHLY("com.maygul.game.rsp.enum.statistic-type.monthly");

  private String label;
}
