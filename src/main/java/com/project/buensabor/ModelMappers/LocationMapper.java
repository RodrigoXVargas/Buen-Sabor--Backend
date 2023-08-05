package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.userDto.LocationDto;
import com.project.buensabor.entities.Location;
import org.springframework.stereotype.Component;

@Component
public class LocationMapper extends ModelMapperEntity<Location, LocationDto> {
}
