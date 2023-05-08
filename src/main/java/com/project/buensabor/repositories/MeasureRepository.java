package com.project.buensabor.repositories;

import com.project.buensabor.entities.Measure;
import com.project.buensabor.entities.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, Long> {

}