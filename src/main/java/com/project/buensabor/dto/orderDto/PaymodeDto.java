package com.project.buensabor.dto.orderDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.enums.PayType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymodeDto extends BaseDto {
    private PayType paymode;
}
