package com.project.buensabor.dto.userDto;

import com.project.buensabor.dto.BaseDto;
import com.project.buensabor.entities.User;
import com.project.buensabor.enums.RolName;
import lombok.*;

import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class RolDto extends BaseDto {

    private RolName rol;

}
