package com.project.buensabor.ModelMappers;

import com.project.buensabor.ModelMappers.Base.ModelMapperEntity;
import com.project.buensabor.dto.orderDto.BillDto;
import com.project.buensabor.entities.Bill;
import org.springframework.stereotype.Component;

@Component
public class BillMapper extends ModelMapperEntity<Bill, BillDto> {
}
