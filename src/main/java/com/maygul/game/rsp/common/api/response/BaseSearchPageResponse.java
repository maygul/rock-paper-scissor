package com.maygul.game.rsp.common.api.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseSearchPageResponse<T> {
  private List<T> rows;
  private long totalCount;
}
