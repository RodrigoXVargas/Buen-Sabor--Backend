package com.project.buensabor.entities;

import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ADDRESSES")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String stread;

    @Column
    private Integer number;

    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name = "user_id")
    private User user;

}
