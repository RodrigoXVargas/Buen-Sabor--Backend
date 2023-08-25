package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.services.Base.BaseServicesDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService extends BaseServicesDTO<ProductDto, Long> {

    List<ProductDto> findProductsActive() throws Exception;

    String changeActive(Long id) throws Exception;

    ProductDto saveOne(ProductDto productDto, MultipartFile image) throws Exception;

    ProductDto updateOne(ProductDto productDto, Long id, MultipartFile image) throws Exception;
}
