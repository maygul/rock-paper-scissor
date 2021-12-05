package com.maygul.game.rsp.api;

import com.maygul.game.rsp.api.response.UserGameSearchPageResponse;
import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.persistence.specification.criteria.UserGameSearchCriteria;
import com.maygul.game.rsp.service.UserGameService;
import com.maygul.game.rsp.service.dto.UserGameDto;
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
@RequestMapping("/api/user-games")
@AllArgsConstructor
public class UserGameController {

  private UserGameService userGameService;

  @GetMapping
  public ResponseEntity<UserGameSearchPageResponse> search(
      @RequestParam(name = "pageNumber", defaultValue = "0", required = false) Integer pageNumber,
      @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize,
      @RequestParam(name = "username", required = false) String username,
      @RequestParam(name = "startDate", required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
      @RequestParam(name = "endDate", required = false)
      @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {

    UserGameSearchCriteria criteria = new UserGameSearchCriteria(username, startDate, endDate);
    UserGameSearchPageResponse response = userGameService.search(pageNumber, pageSize, criteria);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @PostMapping("/play")
  public ResponseEntity<UserGameDto> playGame(@RequestParam(name = "userId") Long userId,
                                              @RequestParam(name = "action") String action)
      throws DataNotFoundException {
    UserGameDto dto = userGameService.playGame(userId, action);
    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

}
