package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.orderDto.OrderDto;
import com.project.buensabor.dto.userDto.AddressDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface OrderService extends BaseServicesDTO<OrderDto, Long> {

    public List<OrderDto> ordersByUserId(Long id) throws Exception;
}
