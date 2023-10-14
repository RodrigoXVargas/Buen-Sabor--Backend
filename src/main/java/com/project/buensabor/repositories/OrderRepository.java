package com.project.buensabor.repositories;

import com.project.buensabor.entities.Order;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

    @Query(value = "SELECT * FROM orders WHERE orders.user_fk = :id", nativeQuery = true)
    List<Order> findOrdersByUserId(@Param("id") long id);

    @Query(value = "SELECT * FROM orders WHERE orders.status_fk = :id", nativeQuery = true)
    List<Order> findOrdersByStatusOrderId(@Param("id") long id);

    @Query(value = "Select * from orders o where o.user_fk = :id and o.creation_date between :desde and :hasta ;", nativeQuery = true)
    List<Order> findOrdersByUserIdBetweenDates(@Param("id") long id,@Param("desde") LocalDate desde, @Param("hasta") LocalDate hasta);

}