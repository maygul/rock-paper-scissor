package com.maygul.game.rsp.api;

import com.maygul.game.rsp.api.response.UserSearchPageResponse;
import com.maygul.game.rsp.exception.app.UsernameAlreadyExistException;
import com.maygul.game.rsp.persistence.specification.criteria.UserSearchCriteria;
import com.maygul.game.rsp.service.UserService;
import com.maygul.game.rsp.service.dto.UserDto;
import io.swagger.annotations.Api;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

  private UserService userService;

  @GetMapping
  public ResponseEntity<UserSearchPageResponse> search(
      @RequestParam(name = "pageNumber", defaultValue = "0") Integer pageNumber,
      @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
      @RequestParam(name = "username", required = false) String username,
      @RequestParam(name = "beginDate", required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
      @RequestParam(name = "endDate", required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

    UserSearchCriteria criteria = new UserSearchCriteria(username, beginDate, endDate);
    UserSearchPageResponse response = userService.search(pageNumber, pageSize, criteria);

    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserDto> create(@RequestParam(name = "username") String username)
      throws UsernameAlreadyExistException {
    UserDto dto = userService.create(username);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }
}
