package com.maygul.game.rsp.service;

import com.maygul.game.rsp.api.response.UserSearchPageResponse;
import com.maygul.game.rsp.exception.app.UsernameAlreadyExistException;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.persistence.entity.User;
import com.maygul.game.rsp.persistence.specification.criteria.UserSearchCriteria;
import com.maygul.game.rsp.service.dto.UserDto;

public interface UserService {

  UserSearchPageResponse search(Integer pageNumber, Integer pageSize, UserSearchCriteria criteria);

  UserDto create(String username) throws UsernameAlreadyExistException;

  UserDto getUserById(Long id) throws DataNotFoundException;

  User getEntityById(Long id) throws DataNotFoundException;
}
