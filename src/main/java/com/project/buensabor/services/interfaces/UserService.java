package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.dto.userDto.UserDto;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface UserService extends BaseServicesDTO<UserDto, Long> {

    List<UserDto> findEmployees() throws Exception;

    String changeRol(RolDto rol, Long id) throws Exception;

    String changeBlacklist(Long id) throws Exception;

    UserDto findUserByEmail(String mail) throws CustomException;
}
