package com.project.buensabor.dto.userDto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.buensabor.entities.Location;
import com.project.buensabor.entities.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddressDto {

    private String street;


    private Integer number;

    private User user;

    private Location location;


}
