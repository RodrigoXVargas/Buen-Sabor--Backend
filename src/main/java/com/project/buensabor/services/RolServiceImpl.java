package com.project.buensabor.services;

import com.project.buensabor.entities.Rol;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.RolService;
import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl extends BaseServicesImpl<Rol, Long> implements RolService {

    public RolServiceImpl(BaseRepository<Rol, Long> baseRepository) {
        super(baseRepository);
    }

}
