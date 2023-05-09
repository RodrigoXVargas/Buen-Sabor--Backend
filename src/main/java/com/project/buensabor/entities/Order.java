package com.project.buensabor.entities;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order extends Base {

    @Column
    private Instant date;

    @Column
    private String withdrawalMode;

    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name = "address_fk")
    @JsonBackReference(value = "address-orders")
    private Address address;


    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name = "user_fk")
    @JsonBackReference(value = "user-orders")
    private User user;





    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name = "status_fk")
    @JsonBackReference(value = "status-orders")
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "DetailOrder",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Product> products;


//    private Payment formPayment;


}
