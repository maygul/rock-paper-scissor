package com.maygul.game.rsp.api.response;

import com.maygul.game.rsp.common.api.response.BaseSearchPageResponse;
import com.maygul.game.rsp.service.dto.StatisticDto;
import java.util.List;

public class StatisticSearchPageResponse extends BaseSearchPageResponse<StatisticDto> {
  public StatisticSearchPageResponse(List<StatisticDto> rows, long totalCount) {
    super(rows, totalCount);
  }
}
