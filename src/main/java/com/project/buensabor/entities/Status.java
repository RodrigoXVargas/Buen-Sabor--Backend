package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "STATUSES")
public class Status extends Base {

    @Column
    private String status;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;


}
