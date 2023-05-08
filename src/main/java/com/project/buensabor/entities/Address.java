package com.project.buensabor.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String street;

    @Column
    private Integer number;

    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne()
    @JoinColumn(name = "location_id")
    private Location location;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Order> orders;

}
