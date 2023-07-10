package com.project.buensabor.entities;

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
@Table(name = "STATUSORDER")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusOrder extends Base {

    @Enumerated(EnumType.STRING)
    @Column
    private StatusType statusType;


}
