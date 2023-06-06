package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.enums.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "STATUSES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Status extends Base {

    @Enumerated(EnumType.STRING)
    @Column
    private StatusType status;

    @OneToMany(mappedBy = "status", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference(value = "status-orders")
    private List<Order> orders;


}
