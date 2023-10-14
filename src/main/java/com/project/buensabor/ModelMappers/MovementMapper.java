package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.orderDto.MovementDto;
import com.project.buensabor.entities.Movement;
import org.springframework.stereotype.Component;

@Component
public class MovementMapper extends ModelMapperEntity<Movement, MovementDto> {
}
