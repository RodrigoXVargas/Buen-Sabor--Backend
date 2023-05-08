package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

@Entity
@Table(name = "ROLES")
public class Rol extends Base {

    @Column
    private String rol;

    @OneToMany(mappedBy = "rol", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "rol-user")
    private List<User> user;

}
