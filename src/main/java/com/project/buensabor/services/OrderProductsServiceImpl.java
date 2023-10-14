package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.OrderProductsMapper;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OrderProductsDto;
import com.project.buensabor.entities.OrderProducts;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.OrderProductsService;
import org.springframework.stereotype.Service;

@Service
public class OrderProductsServiceImpl extends BaseServicesDTOImpl<OrderProducts, OrderProductsDto, OrderProductsMapper, Long> implements OrderProductsService {


    public OrderProductsServiceImpl(BaseRepository<OrderProducts, Long> baseRepository, OrderProductsMapper mapper) {
        super(baseRepository, mapper);
    }
}
