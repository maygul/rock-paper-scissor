package com.maygul.game.rsp.common.api.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseEnumResponse {
  private String label;
  private String value;
}
