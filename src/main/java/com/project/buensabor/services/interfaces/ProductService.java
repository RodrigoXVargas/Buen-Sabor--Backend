package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.Base.BaseServicesDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService extends BaseServicesDTO<ProductDto, Long> {

    List<ProductDto> findProductsActive() throws Exception;

    String changeActive(Long id) throws Exception;

    ProductDto saveOne(ProductDto productDto, MultipartFile image) throws Exception;

    ProductDto updateOne(ProductDto productDto, Long id, MultipartFile image) throws Exception;

    List<ProductDto> findProductsByQDesc() throws Exception;

    boolean validarStock(List<OProductsWithoutOrderDto> productosAValidar) throws CustomException;

    void descontarStock(List<OProductsWithoutOrderDto> productosAValidar) throws CustomException;
}
