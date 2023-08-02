package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.ModelMappers.ProductMapper;
import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServicesDTOImpl<Product, ProductDto, ProductMapper, Long> implements ProductService {


    public ProductServiceImpl(BaseRepository<Product, Long> baseRepository, ProductMapper mapper) {
        super(baseRepository, mapper);
    }
}
