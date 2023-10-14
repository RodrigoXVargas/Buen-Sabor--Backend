package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.userDto.UserDtos.UserDto;
import com.project.buensabor.entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends ModelMapperEntity<User, UserDto> {
}
