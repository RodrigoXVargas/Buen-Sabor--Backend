package com.project.buensabor.repositories;

import com.project.buensabor.entities.User;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    @Query( value = "SELECT * FROM users WHERE users.email = :email", nativeQuery = true )
    Optional<User> findByEmail(@Param( "email" ) String email);


    //boolean existsByEmail(String email);

}