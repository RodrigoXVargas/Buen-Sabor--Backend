package com.project.buensabor.services.interfaces;

import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.dto.productDto.ProductDtos.ProductDto;
import com.project.buensabor.dto.productDto.ProductDtos.ProductRankingDto;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.services.Base.BaseServicesDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService extends BaseServicesDTO<ProductDto, Long> {

    List<ProductDto> findProductsActive() throws CustomException;

    String changeActive(Long id) throws CustomException;

    ProductDto saveOne(ProductDto productDto, MultipartFile image) throws CustomException;

    ProductDto updateOne(ProductDto productDto, Long id, MultipartFile image) throws CustomException;

    List<ProductDto> findProductsByQDesc() throws CustomException;

    boolean validarStock(List<OProductsWithoutOrderDto> productosAValidar) throws CustomException;

    void descontarOReponerStock(List<OProductsWithoutOrderDto> productosAValidar, boolean reponerOdescontar) throws CustomException;

    List<ProductRankingDto> getBestSellingProducts(String desde, String hasta) throws CustomException;
}
