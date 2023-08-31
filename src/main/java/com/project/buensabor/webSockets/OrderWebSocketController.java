package com.project.buensabor.webSockets;

import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.entities.Rol;
import com.project.buensabor.enums.RolName;
import com.project.buensabor.enums.StatusType;
import com.project.buensabor.services.interfaces.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
public class OrderWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private OrderService orderService;


    @SubscribeMapping("/topic/cashiers")
    public List<OrderDto> cashiersSubscription() throws Exception {
        List<OrderDto> orderDtoList = orderService.getOrdersByStatus(1l);
        List<OrderDto> orderDtoList2 = orderService.getOrdersByStatus(3l);
        if (orderDtoList2.size() != 0) {
            for (OrderDto orderDto: orderDtoList2) {
                orderDtoList.add(orderDto);
            }
        }
        return orderDtoList;
    }

    @SubscribeMapping("/topic/chefs")
    public List<OrderDto> chefsSubscription() throws Exception {
        List<OrderDto> orderDtoList = orderService.getOrdersByStatus(2l);
        if (orderDtoList.size() == 0) {
            orderDtoList = new ArrayList<>();
        }
        return orderDtoList;
    }

    @SubscribeMapping("/topic/deliveries")
    public List<OrderDto> deliveriesSubscription() throws Exception {
        List<OrderDto> orderDtoList = orderService.getOrdersByStatus(4l);
        if (orderDtoList.size() == 0) {
            orderDtoList = new ArrayList<>();
        }
        return orderDtoList;
    }

    @MessageMapping("/cashiers")
    @SendTo("/topic/cashiers")
    public List<OrderDto> sendCashiers() throws Exception {
        List<OrderDto> orderDtoList = orderService.getOrdersByStatus(1l);
        List<OrderDto> orderDtoList2 = orderService.getOrdersByStatus(3l);
        if (orderDtoList2.size() != 0) {
            for (OrderDto orderDto: orderDtoList2) {
                orderDtoList.add(orderDto);
            }
        }
        return orderDtoList;
    }
    @MessageMapping("/chefs")
    @SendTo("/topic/chefs")
    public List<OrderDto> sendChefs() throws Exception {
        List<OrderDto> orderDtoList = orderService.getOrdersByStatus(2l);
        if (orderDtoList.size() == 0) {
            orderDtoList = new ArrayList<>();
        }
        return orderDtoList;
    }
    @MessageMapping("/deliveries")
    @SendTo("/topic/deliveries")
    public List<OrderDto> sendDeliveries() throws Exception {
        List<OrderDto> orderDtoList = orderService.getOrdersByStatus(4l);
        if (orderDtoList.size() == 0) {
            orderDtoList = new ArrayList<>();
        }
        return orderDtoList;
    }
}

