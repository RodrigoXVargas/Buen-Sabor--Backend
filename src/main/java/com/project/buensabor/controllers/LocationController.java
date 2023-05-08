package com.project.buensabor.controllers;

import com.project.buensabor.dto.userDto.LocationDto;
import com.project.buensabor.entities.Location;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/location")
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllLocation()
    {
        List<LocationDto> list = locationService.getAllCategories();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public LocationDto getLocationByID(@PathVariable("id") Long locationID)
    {
        LocationDto location = locationService.getLocation(locationID);
        return location;
    }

    @PostMapping("/save")
    public Location saveLocation(@Valid @RequestBody Location location)
    {
        return locationService.saveLocation(location);
    }

    @PutMapping("/update/{id}")
    public Location updateLocation(@RequestBody Location location, @PathVariable("id") Long locationID)
    {
        return locationService.updateLocation(location, locationID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLocationById(@PathVariable("id") Long locationID)
    {
        locationService.deleteLocationById(locationID);
        return "Deleted Successfully";
    }
}
