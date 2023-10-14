package com.project.buensabor.webSockets;

import com.project.buensabor.dto.orderDto.OrderDtos.OrderDto;
import com.project.buensabor.dto.orderDto.OrderDtos.OrderWithoutuserDto;
import com.project.buensabor.dto.userDto.UserDtos.UserDto;
import com.project.buensabor.enums.StatusType;
import com.project.buensabor.services.interfaces.OrderService;
import com.project.buensabor.services.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@Slf4j
public class OrderWebSocketController {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @SubscribeMapping("/topic/cashiers")
    public List<OrderDto> cashiersSubscription() throws Exception {
        List<OrderDto> orderDtoList = orderService.getOrdersByStatus(1l);
        List<OrderDto> orderDtoList2 = orderService.getOrdersByStatus(3l);
        if (orderDtoList2.size() != 0) {
            for (OrderDto orderDto : orderDtoList2) {
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
        try {
            List<OrderDto> orderDtoList = orderService.getOrdersByStatus(1l);
            List<OrderDto> orderDtoList2 = orderService.getOrdersByStatus(3l);
            if (orderDtoList2.size() != 0) {
                for (OrderDto orderDto : orderDtoList2) {
                    orderDtoList.add(orderDto);
                }
            }
            return orderDtoList;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @MessageMapping("/chefs")
    @SendTo("/topic/chefs")
    public List<OrderDto> sendChefs() throws Exception {
        try {
            List<OrderDto> orderDtoList = orderService.getOrdersByStatus(2l);
            if (orderDtoList.size() == 0) {
                orderDtoList = new ArrayList<>();
            }
            return orderDtoList;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @MessageMapping("/deliveries")
    @SendTo("/topic/deliveries")
    public List<OrderDto> sendDeliveries() throws Exception {
        try {
            List<OrderDto> orderDtoList = orderService.getOrdersByStatus(4l);
            if (orderDtoList.size() == 0) {
                orderDtoList = new ArrayList<>();
            }
            return orderDtoList;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @MessageMapping("/private-message")
    public List<OrderWithoutuserDto> sendClient(Long id) throws Exception {
        try {
            UserDto user = userService.findById(id);
            List<StatusType> statusTypes = Arrays.asList(
                    StatusType.In_Queue,
                    StatusType.In_Preparation,
                    StatusType.Ready,
                    StatusType.Out_for_Delivery);
            List<OrderWithoutuserDto> orderDtoList = orderService.ordersByUserId(id);
            List<OrderWithoutuserDto> orderFilters = new ArrayList<>();
            for (OrderWithoutuserDto order : orderDtoList) {
                if (statusTypes.contains(order.getStatusOrder().getStatusType())) {
                    orderFilters.add(order);
                }
            }
            messagingTemplate.convertAndSendToUser(user.getMail(),"/private", orderFilters);
            return orderFilters;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}

