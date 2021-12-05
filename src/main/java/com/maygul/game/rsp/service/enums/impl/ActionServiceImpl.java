package com.maygul.game.rsp.service.enums.impl;

import com.maygul.game.rsp.common.api.response.BaseEnumResponse;
import com.maygul.game.rsp.exception.app.ActionNotFoundException;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.persistence.entity.Action;
import com.maygul.game.rsp.service.enums.ActionService;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Service
public class ActionServiceImpl implements ActionService {

  private MessageSource messageSource;
  private Action[] enums;

  public ActionServiceImpl(MessageSource messageSource) {
    this.messageSource = messageSource;
    enums = Action.values();
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
    Action temp = getEnumByName(name);

    return new BaseEnumResponse(messageSource.getMessage(temp.getLabel(), null, currentLocale),
        temp.name());
  }

  @Override
  public Action getEnumByName(String name) throws DataNotFoundException {
    Action action = Arrays.stream(enums)
        .filter(temp -> temp.name().equals(name))
        .findFirst()
        .orElseThrow(ActionNotFoundException::new);
    return action;
  }

}
