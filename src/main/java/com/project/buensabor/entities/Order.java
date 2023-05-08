package com.project.buensabor.entities;

import ch.qos.logback.core.net.server.Client;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Instant date;

    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name = "user_id")
    @JsonBackReference(value = "user-order")
    private User user;

    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name = "address_id")
    @JsonBackReference(value = "address-orders")
    private Address address;

    @Column
    private String withdrawalMode;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Bill bill;

    @ManyToOne(cascade={ CascadeType.ALL})
    @JoinColumn(name = "status_id")
    @JsonBackReference(value = "status-order")
    private Status status;

    @ManyToMany
    @JoinTable(
            name = "DetailOrder",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Product> products;


//    private Payment formPayment;


}
