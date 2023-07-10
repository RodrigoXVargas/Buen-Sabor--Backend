package com.project.buensabor.repositories;

import com.project.buensabor.entities.StatusOrder;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends BaseRepository<StatusOrder, Long> {

}