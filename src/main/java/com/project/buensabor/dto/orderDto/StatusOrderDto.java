package com.project.buensabor.dto.orderDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.enums.StatusType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatusOrderDto extends BaseDto {

    private StatusType statusType;

}
