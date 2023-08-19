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
import java.util.Objects;

@Controller
public class OrderWebSocketController {

    @Autowired
    private OrderService orderService;

    @MessageMapping("/rols")
    @SendTo("/topic/orderslist")
    public List<OrderDto> getOrderListByRol(Rol rol) throws Exception {
        List<OrderDto> orders;
        int id = 0;
        if (Objects.nonNull(rol.getId())) id = Math.toIntExact(rol.getId());
        switch (id) {
            case 3:
                orders = orderService.getOrdersByStatus(1l);
                List<OrderDto> orders2 = orderService.getOrdersByStatus(3l);
                if (orders2.size() != 0) {
                    for (OrderDto orderDto: orders2) {
                        orders.add(orderDto);
                    }
                }
                break;
            case 4:
                orders = orderService.getOrdersByStatus(2l);
                break;
            case 5:
                orders = orderService.getOrdersByStatus(4l);
                break;
            default:
                orders = new ArrayList<>();
                break;
        }
        return orders;
    }
}
