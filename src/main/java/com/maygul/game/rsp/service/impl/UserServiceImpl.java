package com.maygul.game.rsp.service.impl;

import com.maygul.game.rsp.api.response.UserSearchPageResponse;
import com.maygul.game.rsp.exception.app.UserNotFoundException;
import com.maygul.game.rsp.exception.app.UsernameAlreadyExistException;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.mapper.UserMapper;
import com.maygul.game.rsp.persistence.entity.User;
import com.maygul.game.rsp.persistence.repository.UserRepository;
import com.maygul.game.rsp.persistence.specification.UserSpecification;
import com.maygul.game.rsp.persistence.specification.criteria.UserSearchCriteria;
import com.maygul.game.rsp.service.UserService;
import com.maygul.game.rsp.service.dto.UserDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private UserMapper userMapper;

  public UserServiceImpl(UserRepository userRepository,
                         UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public UserDto create(String username) throws UsernameAlreadyExistException {
    checkIfUserExistWithName(username);

    User user = new User();
    user.setUsername(username);

    userRepository.save(user);
    return userMapper.toDto(user);
  }

  private void checkIfUserExistWithName(String username) throws UsernameAlreadyExistException {
    List<User> users = userRepository.findAllByUsername(username);
    if (users != null && !users.isEmpty()) {
      throw new UsernameAlreadyExistException();
    }
  }

  @Override
  public UserSearchPageResponse search(Integer pageNumber, Integer pageSize,
                                       UserSearchCriteria criteria) {
    Specification<User> specification = UserSpecification.findByCriteria(criteria);
    Pageable pageable = PageRequest.of(pageNumber, pageSize);

    Page<User> page = userRepository.findAll(specification, pageable);
    List<User> users = page.getContent();
    long totalCount = page.getTotalElements();

    UserSearchPageResponse response =
        new UserSearchPageResponse(userMapper.toDto(users), totalCount);
    return response;
  }

  @Override
  public UserDto getUserById(Long id) throws DataNotFoundException {
    User user = getEntityById(id);
    return userMapper.toDto(user);
  }

  @Override
  public User getEntityById(Long id) throws DataNotFoundException {
    return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
  }
}
