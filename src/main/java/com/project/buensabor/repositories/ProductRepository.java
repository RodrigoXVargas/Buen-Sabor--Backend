package com.project.buensabor.repositories;

import com.project.buensabor.dto.productDto.ProductDtos.ProductRanking;
import com.project.buensabor.dto.productDto.ProductDtos.ProductRankingDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends BaseRepository<Product, Long> {

    @Query(value = "SELECT * FROM products WHERE products.active = true", nativeQuery = true)
    List<Product> findAllByActive();

    @Query(value = "SELECT * FROM products order by products.quantity_sold desc", nativeQuery = true)
    List<Product> getProductsOrderByQuantitySoldDesc();

    @Query(value = "SELECT p.id, p.name, p.subcategory_fk, p.active, p.cost, p.price, " +
            "SUM(op.cantidad) as 'quantity_sold', ((SUM(op.cantidad))*p.cost) as 'total_cost', ((p.price-p.cost)*SUM(op.cantidad)) as 'total_profit' " +
            "FROM products p " +
            "inner join order_products op on p.id = op.product_fk " +
            "inner join orders o on o.id = op.order_fk " +
            "where o.status_fk = 5 and o.creation_date between :desde and :hasta " +
            "group by p.id " +
            "order by quantity_sold desc;", nativeQuery = true)
    List<ProductRanking> rankingProductsByDates(LocalDate desde, LocalDate hasta);
}