package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ADDRESSES")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_address;

    @Column
    private String street;

    @Column
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "user", referencedColumnName = "id_user")
    @JsonBackReference(value = "user-address")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_id",referencedColumnName = "id_location")
    @JsonBackReference(value = "location-address")
    private Location location;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "address-orders")
    private List<Order> orders;

}
