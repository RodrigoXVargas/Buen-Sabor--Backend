package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.enums.TypeMovement;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "MOVEMENTS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movement extends Base {

    @Column
    @Enumerated(EnumType.STRING)
    private TypeMovement type;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime date;

    @Column
    private String description;

    @Column
    private Double total;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "order_fk")
    private Order order;

}
