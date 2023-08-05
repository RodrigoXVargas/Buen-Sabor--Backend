package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.entities.StatusOrder;
import org.springframework.stereotype.Component;

@Component
public class StatusOrderMapper extends ModelMapperEntity<StatusOrder, StatusOrderDto> {
}
