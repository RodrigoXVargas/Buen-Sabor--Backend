package com.project.buensabor.services;

import com.project.buensabor.entities.Order;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends BaseServicesImpl<Order, Long> implements OrderService {

    public OrderServiceImpl(BaseRepository<Order, Long> baseRepository) {
        super(baseRepository);
    }

}
