package com.project.buensabor.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.project.buensabor.entities.Base.Base;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "SECTIONS")
public class Section extends Base {

    @Column
    private String section;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "section-locations")
    private List<Location> locations;

}
