package com.project.buensabor.repositories;

import com.project.buensabor.entities.Bill;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends BaseRepository<Bill, Long> {

}