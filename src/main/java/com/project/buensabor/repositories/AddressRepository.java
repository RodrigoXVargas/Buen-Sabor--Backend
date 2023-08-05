package com.project.buensabor.repositories;

import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends BaseRepository<Address, Long> {

    @Query(value = "SELECT * FROM addresses WHERE addresses.user_fk = :id", nativeQuery = true)
    List<Address> findAddressesByUserId(@Param("id") long id);


}