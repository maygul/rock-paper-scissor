package com.maygul.game.rsp.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StatisticDto {
  private String username;
  private int score;
}
