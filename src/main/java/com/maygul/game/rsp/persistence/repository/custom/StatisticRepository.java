package com.maygul.game.rsp.persistence.repository.custom;

import com.maygul.game.rsp.persistence.entity.StatisticType;
import com.maygul.game.rsp.service.dto.StatisticDto;
import java.util.List;

public interface StatisticRepository {

  List<StatisticDto> getStatistics(StatisticType type);
}
