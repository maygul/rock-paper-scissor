package com.maygul.game.rsp.api;

import com.maygul.game.rsp.common.api.response.BaseEnumResponse;
import com.maygul.game.rsp.service.enums.ActionService;
import com.maygul.game.rsp.service.enums.StatisticTypeService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consts")
@RequiredArgsConstructor
public class ConstController {

  private final StatisticTypeService statisticTypeService;
  private final ActionService actionService;

  @GetMapping("/actions")
  public ResponseEntity<List<BaseEnumResponse>> actions() {
    List<BaseEnumResponse> response = actionService.getAll();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @GetMapping("/statisticTypes")
  public ResponseEntity<List<BaseEnumResponse>> statisticTypes() {
    List<BaseEnumResponse> response = statisticTypeService.getAll();
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
