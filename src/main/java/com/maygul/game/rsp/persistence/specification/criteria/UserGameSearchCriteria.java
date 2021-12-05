package com.maygul.game.rsp.persistence.specification.criteria;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserGameSearchCriteria {
  private String username;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
}
