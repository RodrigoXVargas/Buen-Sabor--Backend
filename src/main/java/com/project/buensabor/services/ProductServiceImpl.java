package com.project.buensabor.services;

import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl extends BaseServicesImpl<Product, Long> implements ProductService {

    public ProductServiceImpl(BaseRepository<Product, Long> baseRepository) {
        super(baseRepository);
    }

}
