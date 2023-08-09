package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface UserService extends BaseServicesDTO<UserDto, Long> {

    List<UserDto> findEmployees() throws Exception;
}
