package com.project.buensabor.services;

import com.project.buensabor.dto.userDto.LocationDto;
import com.project.buensabor.entities.Location;
import com.project.buensabor.repositories.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {


    private final LocationRepository locationRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    public List<LocationDto> getAllCategories() {
        List<LocationDto> result = new ArrayList<>();
        List<Location> list = locationRepository.findAll();

        for (Location location : list) {
            LocationDto locationDto = LocationDto.builder().location(location.getLocation()).section(location.getSection()).build();
            result.add(locationDto);
        }
        return result;
    }

    public LocationDto getLocation(Long locationID) {
        Optional<Location> location = locationRepository.findById(locationID);
        if(!(location.isEmpty())) {
            return LocationDto.builder().location(location.get().getLocation()).section(location.get().getSection()).build();
        }
        return null;
    }

    public Location updateLocation(Location location, Long locationID) {
        Optional<Location> locationList = locationRepository.findById(locationID);
        if (locationList.get().getId_location() == location.getId_location()) {
            return locationRepository.save(location);
        } else {
            return null;
        }
    }

    public void deleteLocationById(Long locationID) {
        locationRepository.deleteById(locationID);
    }
}
