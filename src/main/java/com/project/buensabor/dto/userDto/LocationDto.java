package com.project.buensabor.dto.userDto;

import com.project.buensabor.entities.Section;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LocationDto {

    private String location;

    private Section section;

}
