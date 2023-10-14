package com.project.buensabor.dto.userDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.enums.RolName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolDto extends BaseDto {

    private RolName rol;

}
