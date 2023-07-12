package com.project.buensabor.services;

import com.project.buensabor.entities.OrderProducts;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.OrderProductsService;
import org.springframework.stereotype.Service;

@Service
public class OrderProductsServiceImpl extends BaseServicesImpl<OrderProducts, Long> implements OrderProductsService {

    public OrderProductsServiceImpl(BaseRepository<OrderProducts, Long> baseRepository) {
        super(baseRepository);
    }
}
