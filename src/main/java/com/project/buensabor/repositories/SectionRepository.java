package com.project.buensabor.repositories;

import com.project.buensabor.entities.Category;
import com.project.buensabor.entities.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

}