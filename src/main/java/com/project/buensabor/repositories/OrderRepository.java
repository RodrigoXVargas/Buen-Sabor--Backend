package com.project.buensabor.repositories;

import com.project.buensabor.entities.Order;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends BaseRepository<Order, Long> {

}