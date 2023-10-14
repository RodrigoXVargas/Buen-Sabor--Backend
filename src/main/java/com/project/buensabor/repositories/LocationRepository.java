package com.project.buensabor.repositories;

import com.project.buensabor.entities.Location;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends BaseRepository<Location, Long> {

}