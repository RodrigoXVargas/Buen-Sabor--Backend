package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ADDRESSES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address extends Base {

    @Column
    private String street;

    @Column
    private Integer number;

    @ManyToOne
    @JoinColumn(name = "user_fk")
    @JsonBackReference(value = "user-addresses")
    private User user;

    @ManyToOne
    @JoinColumn(name = "location_fk")
    @JsonBackReference(value = "location-addresses")
    private Location location;

    @OneToMany(mappedBy = "address", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "address-orders")
    private List<Order> orders;

}
