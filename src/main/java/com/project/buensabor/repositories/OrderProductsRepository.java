package com.project.buensabor.repositories;

import com.project.buensabor.entities.OrderProducts;
import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderProductsRepository extends BaseRepository<OrderProducts, Long> {

    @Query(value = "SELECT * FROM order_products WHERE order_products.order_fk = :id", nativeQuery = true)
    List<OrderProducts> findOrderProductsByOrderId (@Param("id") long id);

}
