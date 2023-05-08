package com.project.buensabor.dto.userDto;

import com.project.buensabor.entities.Location;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SectionDto {

    private String section;

}
