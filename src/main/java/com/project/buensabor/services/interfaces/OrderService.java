package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderWithoutuserDto;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface OrderService extends BaseServicesDTO<OrderDto, Long> {

    public List<OrderWithoutuserDto> ordersByUserId(Long id) throws Exception;

    public List<OrderDto> getOrdersByStatus(Long id) throws Exception;

    String changeStatus(StatusOrderDto status, Long id) throws Exception;
}
