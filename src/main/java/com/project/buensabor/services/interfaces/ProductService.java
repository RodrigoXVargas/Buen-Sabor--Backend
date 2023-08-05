package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.services.Base.BaseServicesDTO;

import java.util.List;

public interface ProductService extends BaseServicesDTO<ProductDto, Long> {

    List<ProductDto> findProductsActive() throws Exception;
}
