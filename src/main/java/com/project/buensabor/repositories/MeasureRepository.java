package com.project.buensabor.repositories;

import com.project.buensabor.entities.Measure;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends BaseRepository<Measure, Long> {

}