package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.dto.userDto.UserDtos.UserDto;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.Base.BaseServicesDTO;

public interface StatusService extends BaseServicesDTO<StatusOrderDto, Long> {

    StatusOrderDto getStatusOrder(String status) throws CustomException;
}
