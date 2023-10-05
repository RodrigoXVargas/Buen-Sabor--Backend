package com.project.buensabor.repositories;

import com.project.buensabor.entities.StatusOrder;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StatusRepository extends BaseRepository<StatusOrder, Long> {

    @Query( value = "SELECT * FROM statusorder WHERE statusorder.status_type = :status", nativeQuery = true )
    Optional<StatusOrder> findByStatusType(@Param( "status" ) String status);
}