package com.project.buensabor.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String password;

    @Column
    private Boolean blacklist;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rol_id", referencedColumnName = "rol")
    private Rol rol;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;


}
