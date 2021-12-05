package com.maygul.game.rsp.mapper;

import com.maygul.game.rsp.common.mapper.EntityMapper;
import com.maygul.game.rsp.persistence.entity.UserGame;
import com.maygul.game.rsp.service.dto.UserGameDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserGameMapper extends EntityMapper<UserGameDto, UserGame> {

  UserGameDto toDto(UserGame entity);
}
