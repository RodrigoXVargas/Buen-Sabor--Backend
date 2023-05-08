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
@Table(name = "LOCATIONS")
public class Location extends Base {

    @Column
    private String location;

    @ManyToOne()
    @JoinColumn(name = "section_id", referencedColumnName = "id_section")
    @JsonBackReference(value = "section-location")
    private Section section;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference(value = "location-address")
    private List<Address> addresses;
}
