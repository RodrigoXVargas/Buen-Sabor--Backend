package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.entities.ModelMappers.RolMapper;
import com.project.buensabor.entities.Rol;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.RolService;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends BaseServicesDTOImpl<Rol, RolDto, RolMapper, Long> implements RolService {


    public RolServiceImpl(BaseRepository<Rol, Long> baseRepository, RolMapper mapper) {
        super(baseRepository, mapper);
    }
}
