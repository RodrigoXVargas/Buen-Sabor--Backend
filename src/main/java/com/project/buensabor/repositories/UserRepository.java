package com.project.buensabor.repositories;

import com.project.buensabor.dto.userDto.UserDtos.UserRanking;
import com.project.buensabor.entities.User;
import com.project.buensabor.repositories.Base.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

    @Query( value = "SELECT * FROM users WHERE users.mail = :email", nativeQuery = true )
    Optional<User> findByEmail(@Param( "email" ) String email);

    @Query(value = "SELECT * FROM users WHERE users.rol_fk <> 6;", nativeQuery = true)
    List<User> findAllEmployees();


    boolean existsUserByMail(String mail);

    @Query(value = "SELECT u.id, u.first_name, u.last_name, u.blacklist, u.mail, u.telephone, " +
            "count(o.id) as 'orders_quantity', Sum(total_price) as 'total_sum' " +
            "FROM users u " +
            "inner join orders o on u.id = o.user_fk " +
            "where o.status_fk = 5 and o.creation_date between :desde and :hasta " +
            "group by u.id " +
            "order by total_sum desc;", nativeQuery = true)
    List<UserRanking> usersRankingByDates(LocalDate desde, LocalDate hasta);

}