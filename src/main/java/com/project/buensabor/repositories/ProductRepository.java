package com.project.buensabor.repositories;

import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    @Query(value = "SELECT * FROM products WHERE products.active = true", nativeQuery = true)
    List<Product> findAllByActive();

    @Query(value = "SELECT * FROM products order by products.quantity_sold desc", nativeQuery = true)
    List<Product> getProductsOrderByQuantitySoldDesc();


}