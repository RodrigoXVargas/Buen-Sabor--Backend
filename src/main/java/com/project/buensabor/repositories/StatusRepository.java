package com.project.buensabor.repositories;

import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Status;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends BaseRepository<Status, Long> {

}