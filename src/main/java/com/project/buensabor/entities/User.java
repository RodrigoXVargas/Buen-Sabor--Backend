package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.enums.StatusUser;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User extends Base {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Long telephone;

    @Column(unique = true)
    private String mail;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusUser blacklist;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "rol_fk")
    private Rol rol;


}
