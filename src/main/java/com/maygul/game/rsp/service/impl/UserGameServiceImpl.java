package com.maygul.game.rsp.service.impl;

import com.maygul.game.rsp.api.response.UserGameSearchPageResponse;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.mapper.UserGameMapper;
import com.maygul.game.rsp.persistence.entity.Action;
import com.maygul.game.rsp.persistence.entity.User;
import com.maygul.game.rsp.persistence.entity.UserGame;
import com.maygul.game.rsp.persistence.repository.UserGameRepository;
import com.maygul.game.rsp.persistence.specification.UserGameSpecification;
import com.maygul.game.rsp.persistence.specification.criteria.UserGameSearchCriteria;
import com.maygul.game.rsp.service.UserGameService;
import com.maygul.game.rsp.service.UserService;
import com.maygul.game.rsp.service.dto.UserGameDto;
import com.maygul.game.rsp.service.enums.ActionService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserGameServiceImpl implements UserGameService {

  private Action[] actions;
  private UserGameRepository userGameRepository;
  private UserService userService;
  private UserGameMapper userGameMapper;
  private ActionService actionService;

  public UserGameServiceImpl(UserService userService,
                             UserGameRepository userGameRepository,
                             UserGameMapper userGameMapper,
                             ActionService actionService) {
    this.userService = userService;
    this.userGameRepository = userGameRepository;
    this.userGameMapper = userGameMapper;
    this.actionService = actionService;
    actions = Action.values();
  }

  @Override
  public UserGameSearchPageResponse search(Integer pageNumber, Integer pageSize,
                                           UserGameSearchCriteria criteria) {
    Specification<UserGame> specification = UserGameSpecification.findByCriteria(criteria);
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<UserGame> page = userGameRepository.findAll(specification, pageable);

    List<UserGame> content = page.getContent();
    long totalCount = page.getTotalElements();

    List<UserGameDto> dtos = userGameMapper.toDto(content);

    dtos.stream().forEach(dto -> {
      try {
        dto.setAppActionDescription(actionService.getByName(dto.getAppAction().name()).getLabel());
        dto.setUserActionDescription(
            actionService.getByName(dto.getUserAction().name()).getLabel());
      } catch (DataNotFoundException ex) {
        log.error(ExceptionUtils.getStackTrace(ex));
      }
    });
    UserGameSearchPageResponse response =
        new UserGameSearchPageResponse(dtos, totalCount);
    return response;
  }

  @Override
  public UserGameDto playGame(Long userId, String action) throws DataNotFoundException {
    User player = userService.getEntityById(userId);
    Action userAction = actionService.getEnumByName(action);

    Action appAction = generateRandomAction();

    int clashResult = Action.resultOfClash(userAction, appAction);

    UserGame newGame = new UserGame();
    newGame.setAppAction(appAction);
    newGame.setUserAction(userAction);
    newGame.setScore(clashResult);
    newGame.setUser(player);

    userGameRepository.save(newGame);

    UserGameDto dto = userGameMapper.toDto(newGame);
    dto.setAppActionDescription(actionService.getByName(appAction.name()).getLabel());
    dto.setUserActionDescription(actionService.getByName(userAction.name()).getLabel());
    return dto;
  }

  private Action generateRandomAction() {
    int random = (int) (Math.random() * 3);
    return actions[random];
  }
}
