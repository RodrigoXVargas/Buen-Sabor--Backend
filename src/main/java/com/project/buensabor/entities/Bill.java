package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "BILLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bill extends Base {

    @Column
    private String date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_fk")
    private Order order;

}
