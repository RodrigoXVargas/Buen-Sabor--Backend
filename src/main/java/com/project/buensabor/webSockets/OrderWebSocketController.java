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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.management.relation.Role;
import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderWebSocketController {

    @Autowired
    private OrderService orderService;

    @MessageMapping("/orders/{id}")
    @SendTo("/topic/orders/{id}")
    public List<OrderDto> getOrdersForRole(@PathVariable Long id) throws Exception {
        // Lógica para obtener órdenes según el rol
        List<OrderDto> orders = orderService.getOrdersByStatus(id);
        return orders;
    }
}
