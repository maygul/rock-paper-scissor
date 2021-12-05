package com.maygul.game.rsp.persistence.specification.criteria;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserSearchCriteria {
  private String username;
  private LocalDateTime beginDate;
  private LocalDateTime endDate;
}
