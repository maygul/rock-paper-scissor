package com.maygul.game.rsp.persistence.repository.custom.impl;

import com.maygul.game.rsp.persistence.entity.StatisticType;
import com.maygul.game.rsp.persistence.repository.custom.StatisticRepository;
import com.maygul.game.rsp.service.dto.StatisticDto;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public class StatisticRepositoryImpl implements StatisticRepository {

  @PersistenceContext
  EntityManager entityManager;

  @Override
  public List<StatisticDto> getStatistics(StatisticType type) {
    LocalDateTime startDate = null;
    LocalDateTime endDate = null;

    LocalDateTime now = LocalDateTime.now();
    if (type.equals(StatisticType.HOURLY)) {
      startDate = now.minusHours(1);
      endDate = now;
    } else if (type.equals(StatisticType.DAILY)) {
      startDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(0, 0, 0));
      endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
    } else if (type.equals(StatisticType.WEEKLY)) {
      startDate = LocalDateTime.of(LocalDate.now().minusWeeks(1), LocalTime.of(0, 0, 0));
      endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
    } else if (type.equals(StatisticType.MONTHLY)) {
      startDate = LocalDateTime.of(LocalDate.now().minusMonths(1), LocalTime.of(0, 0, 0));
      endDate = LocalDateTime.of(LocalDate.now(), LocalTime.of(23, 59, 59));
    }


    Query nativeQuery = entityManager.createNativeQuery(
        "select us.user_name as username,sum(usm.score) as score from user_ us join user_match usm on us.id = usm.user_id where usm.create_date >= ? and usm.create_date <=? group by usm.user_id  order by score desc");

    nativeQuery.setParameter(1, startDate);
    nativeQuery.setParameter(2, endDate);
    nativeQuery.setMaxResults(100);
    List<StatisticDto> result = (List<StatisticDto>) nativeQuery.getResultList();
    return result;
  }
}
