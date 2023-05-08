package com.project.buensabor.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "STATUSES")
public class Status {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String status;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;


}
