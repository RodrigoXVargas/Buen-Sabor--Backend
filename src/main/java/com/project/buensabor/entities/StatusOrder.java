package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.enums.StatusType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
