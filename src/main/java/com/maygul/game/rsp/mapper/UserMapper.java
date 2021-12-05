package com.maygul.game.rsp.mapper;

import com.maygul.game.rsp.common.mapper.EntityMapper;
import com.maygul.game.rsp.persistence.entity.User;
import com.maygul.game.rsp.service.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper extends EntityMapper<UserDto, User> {

}
