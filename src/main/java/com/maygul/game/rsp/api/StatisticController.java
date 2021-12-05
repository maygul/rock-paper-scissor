package com.maygul.game.rsp.api;

import com.maygul.game.rsp.exception.type.DataNotFoundException;
import com.maygul.game.rsp.service.StatisticService;
import com.maygul.game.rsp.service.dto.StatisticDto;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/api/statistics")
@AllArgsConstructor
public class StatisticController {

  private StatisticService statisticService;

  @GetMapping
  public ResponseEntity<List<StatisticDto>> search(
      @RequestParam(name = "statisticType") String statisticType) throws DataNotFoundException {

    List<StatisticDto> response = statisticService.search(statisticType);
    return new ResponseEntity<>(response, HttpStatus.OK);
  }
}
