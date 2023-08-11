package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderWithoutuserDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface OrderService extends BaseServicesDTO<OrderDto, Long> {

    public List<OrderWithoutuserDto> ordersByUserId(Long id) throws Exception;
}
