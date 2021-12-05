package com.maygul.game.rsp.service;

import com.maygul.game.rsp.api.response.UserGameSearchPageResponse;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.persistence.specification.criteria.UserGameSearchCriteria;
import com.maygul.game.rsp.service.dto.UserGameDto;

public interface UserGameService {
  UserGameSearchPageResponse search(Integer pageNumber, Integer pageSize, UserGameSearchCriteria criteria);

  UserGameDto playGame(Long userId, String action) throws DataNotFoundException;
}
