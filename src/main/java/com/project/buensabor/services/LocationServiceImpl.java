package com.project.buensabor.services;

import com.project.buensabor.entities.Location;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.LocationService;
import org.springframework.stereotype.Service;

@Service
public class LocationServiceImpl extends BaseServicesImpl<Location, Long> implements LocationService {

    public LocationServiceImpl(BaseRepository<Location, Long> baseRepository) {
        super(baseRepository);
    }

}
