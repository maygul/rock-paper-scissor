package com.maygul.game.rsp.service.impl;

import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.persistence.entity.StatisticType;
import com.maygul.game.rsp.persistence.repository.custom.StatisticRepository;
import com.maygul.game.rsp.service.StatisticService;
import com.maygul.game.rsp.service.dto.StatisticDto;
import com.maygul.game.rsp.service.enums.StatisticTypeService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatisticServiceImpl implements StatisticService {

  private final StatisticTypeService statisticTypeService;
  private final StatisticRepository statisticRepository;

  private static List<StatisticDto> HOURLY_STATISTICS = new ArrayList<>();
  private static List<StatisticDto> DAILY_STATISTICS = new ArrayList<>();
  private static List<StatisticDto> WEEKLY_STATISTICS = new ArrayList<>();
  private static List<StatisticDto> MONTHLY_STATITICS = new ArrayList<>();

  @Override
  public List<StatisticDto> search(String statisticTypeStr) throws DataNotFoundException {
    StatisticType statisticType = statisticTypeService.getEnumByName(statisticTypeStr);
    List<StatisticDto> response = null;
    if (statisticType.equals(StatisticType.HOURLY)) {
      response = HOURLY_STATISTICS;
    } else if (statisticType.equals(StatisticType.DAILY)) {
      response = DAILY_STATISTICS;
    } else if (statisticType.equals(StatisticType.WEEKLY)) {
      response = WEEKLY_STATISTICS;
    } else if (statisticType.equals(StatisticType.MONTHLY)) {
      response = MONTHLY_STATITICS;
    }
    return response;
  }

  //@Scheduled(cron = "0 0 * * * *")
  @Scheduled(cron = "*/10 * * * * *")
  public void hourlyStatisticCalculation() {
    List<StatisticDto> statistics = statisticRepository.getStatistics(StatisticType.HOURLY);
    HOURLY_STATISTICS = statistics;
  }

  @Scheduled(cron = "0 0 0 * * *")
  public void dailyStatisticCalculation() {
    List<StatisticDto> statistics = statisticRepository.getStatistics(StatisticType.DAILY);
    DAILY_STATISTICS = statistics;
  }

  @Scheduled(cron = "0 0 0 * * 0")
  public void weeklyStatisticCalculation() {
    List<StatisticDto> statistics = statisticRepository.getStatistics(StatisticType.WEEKLY);
    WEEKLY_STATISTICS = statistics;
  }

  @Scheduled(cron = "0 0 0 1 * *")
  public void setMonthlyStatiticCalculation() {
    List<StatisticDto> statistics = statisticRepository.getStatistics(StatisticType.MONTHLY);
    MONTHLY_STATITICS = statistics;
  }

}
