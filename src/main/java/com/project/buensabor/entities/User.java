package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "USERS")
public class User extends Base {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String mail;

    @Column
    private String password;

    @Column(columnDefinition="tinyint(1) default 0")
    private Boolean blacklist;

    @ManyToOne(cascade={ CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "rol_id")
    @JsonBackReference(value = "rol-user")
    private Rol rol;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-address")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "user-order")
    private List<Order> orders;


}
