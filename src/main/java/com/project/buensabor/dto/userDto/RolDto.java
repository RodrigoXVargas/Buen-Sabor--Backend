package com.project.buensabor.dto.userDto;

import com.project.buensabor.entities.User;
import com.project.buensabor.enums.RolName;
import lombok.*;

import java.util.List;

@Builder
@Data
public class RolDto {

    private Long id;
    private RolName rol;

}
