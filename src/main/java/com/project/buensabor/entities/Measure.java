package com.project.buensabor.entities;

import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "MEASURES")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Measure extends Base {

    @Column
    private String measure;

}
