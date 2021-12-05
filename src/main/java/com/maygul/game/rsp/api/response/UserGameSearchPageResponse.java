package com.maygul.game.rsp.api.response;

import com.maygul.game.rsp.common.api.response.BaseSearchPageResponse;
import com.maygul.game.rsp.service.dto.UserGameDto;
import java.util.List;

public class UserGameSearchPageResponse extends BaseSearchPageResponse<UserGameDto> {
  public UserGameSearchPageResponse(List<UserGameDto> rows, long totalCount) {
    super(rows, totalCount);
  }
}
