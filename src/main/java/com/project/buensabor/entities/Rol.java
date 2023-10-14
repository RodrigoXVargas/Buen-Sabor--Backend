package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import com.project.buensabor.enums.RolName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ROLS")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Rol extends Base {

    @Enumerated(EnumType.STRING)
    @Column
    private RolName rol;

    public Rol(Long id, RolName rol) {
        super(id);
        this.rol = rol;
    }
}
