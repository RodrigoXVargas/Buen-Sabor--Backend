package com.project.buensabor.repositories;

import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

}