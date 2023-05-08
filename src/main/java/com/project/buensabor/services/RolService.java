package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.RolDto;
import com.project.buensabor.entities.Rol;
import com.project.buensabor.repositories.RolRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RolService {


    private final RolRepository rolRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public RolService(RolRepository rolRepository) {
        this.rolRepository = rolRepository;
    }

    public Rol saveRol(Rol rol) {
        return rolRepository.save(rol);
    }

    public List<RolDto> getAllRoles() {
        List<RolDto> result = new ArrayList<>();
        List<Rol> list = rolRepository.findAll();

        for (Rol rol : list) {
            RolDto rolDto = RolDto.builder().rol(rol.getRol()).build();
            result.add(rolDto);
        }


        return result;
    }

    public Optional<Rol> getRol(Long rolID) {
        return rolRepository.findById(rolID);
    }

    public Rol updateRol(Rol rol, Long rolID) {
        Optional<Rol> rolList = rolRepository.findById(rolID);
        if (rolList.get().getId() == rol.getId()) {
            return rolRepository.save(rol);
        } else {
            return null;
        }
    }

    public void deleteRolById(Long rolID) {
        rolRepository.deleteById(rolID);
    }
}
