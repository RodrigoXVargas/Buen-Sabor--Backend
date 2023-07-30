package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.LocationDto;
import com.project.buensabor.entities.Location;
import com.project.buensabor.entities.ModelMappers.LocationMapper;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends BaseServicesDTOImpl<Location, LocationDto, LocationMapper, Long> implements LocationService {


    public LocationServiceImpl(BaseRepository<Location, Long> baseRepository, LocationMapper mapper) {
        super(baseRepository, mapper);
    }
}
