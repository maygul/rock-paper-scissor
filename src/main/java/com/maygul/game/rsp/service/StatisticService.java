package com.maygul.game.rsp.service;

import com.maygul.game.rsp.api.response.StatisticSearchPageResponse;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.service.dto.StatisticDto;
import java.util.List;

public interface StatisticService {
  List<StatisticDto> search(String statisticType) throws DataNotFoundException;
}
