package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.enums.PayType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PAYMODES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Paymode extends Base {

    @Enumerated(EnumType.STRING)
    @Column
    private PayType paymode;
}
