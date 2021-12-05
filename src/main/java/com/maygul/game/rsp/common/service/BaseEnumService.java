package com.maygul.game.rsp.common.service;

import com.maygul.game.rsp.common.api.response.BaseEnumResponse;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import java.util.List;

public interface BaseEnumService<T> {

  List<BaseEnumResponse> getAll();

  BaseEnumResponse getByName(String name) throws DataNotFoundException;

  T getEnumByName(String name) throws DataNotFoundException;
}

