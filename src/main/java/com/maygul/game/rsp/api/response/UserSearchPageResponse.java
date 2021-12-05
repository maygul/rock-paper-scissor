package com.maygul.game.rsp.api.response;

import com.maygul.game.rsp.common.api.response.BaseSearchPageResponse;
import com.maygul.game.rsp.service.dto.UserDto;
import java.util.List;


public class UserSearchPageResponse extends BaseSearchPageResponse<UserDto> {

  public UserSearchPageResponse(List<UserDto> rows, long totalCount) {
    super(rows, totalCount);
  }
}
