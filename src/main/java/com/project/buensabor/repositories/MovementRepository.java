package com.project.buensabor.repositories;

import com.project.buensabor.entities.Movement;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MovementRepository extends BaseRepository<Movement, Long> {

    @Query(value= "SELECT * FROM movements m where type like :type  and m.date between :desde and :hasta ;", nativeQuery = true)
    List<Movement> getMovementsByDateBetween(LocalDate desde, LocalDate hasta, String type);

}