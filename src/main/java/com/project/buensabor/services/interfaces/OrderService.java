package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderWithoutuserDto;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.time.LocalDate;
import java.util.List;

public interface OrderService extends BaseServicesDTO<OrderDto, Long> {

    public List<OrderWithoutuserDto> ordersByUserId(Long id) throws CustomException;

    public List<OrderDto> getOrdersByStatus(Long id) throws CustomException;

    String changeStatus(StatusOrderDto status, Long id) throws CustomException;

    String plusMinutesOrder(Long idOrder, Long minutes) throws CustomException;

    List<OrderWithoutuserDto> getOrdersByIdUserBetweenDates(Long id, LocalDate desde, LocalDate hasta) throws CustomException;
}
