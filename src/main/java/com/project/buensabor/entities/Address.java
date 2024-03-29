package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_fk")
    private User user;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "location_fk")
    private Location location;

}
