package com.maygul.game.rsp.service.enums.impl;

import com.maygul.game.rsp.common.api.response.BaseEnumResponse;
import com.maygul.game.rsp.exception.app.StatisticTypeNotFoundException;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.persistence.entity.StatisticType;
import com.maygul.game.rsp.service.enums.StatisticTypeService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class StatisticTypeServiceImpl implements StatisticTypeService {

  private MessageSource messageSource;
  private StatisticType[] enums;

  public StatisticTypeServiceImpl(MessageSource messageSource) {
    this.messageSource = messageSource;
    enums = StatisticType.values();
  }

  @Override
  public List<BaseEnumResponse> getAll() {
    Locale currentLocale = LocaleContextHolder.getLocale();
    List<BaseEnumResponse> responseList = new ArrayList<>();
    Arrays.stream(enums)
        .forEach(temp -> responseList.add(
            new BaseEnumResponse(messageSource.getMessage(temp.getLabel(), null, currentLocale),
                temp.name())));
    return responseList;
  }

  @Override
  public BaseEnumResponse getByName(String name) throws DataNotFoundException {
    Locale currentLocale = LocaleContextHolder.getLocale();
    StatisticType temp = getEnumByName(name);
    return new BaseEnumResponse(messageSource.getMessage(temp.getLabel(), null, currentLocale),
        temp.name());
  }

  @Override
  public StatisticType getEnumByName(String name) throws DataNotFoundException {
    StatisticType temp = Arrays.stream(enums)
        .filter(statisticType -> statisticType.name().equals(name))
        .findFirst()
        .orElseThrow(StatisticTypeNotFoundException::new);
    return temp;
  }
}
