package com.project.buensabor.repositories;

import com.project.buensabor.entities.User;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}