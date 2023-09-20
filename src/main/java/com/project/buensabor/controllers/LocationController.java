package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.userDto.LocationDto;
import com.project.buensabor.entities.Location;
import com.project.buensabor.services.LocationServiceImpl;
import com.project.buensabor.services.interfaces.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api/locations")
public class LocationController extends BaseControllerImpl<Location, LocationDto, LocationServiceImpl> {

    @Autowired
    private LocationService locationService;

    @PreAuthorize("hasAnyAuthority('location:save', '_superAdmin')")
    @PostMapping("/save")
    public ResponseEntity<?> saveOne(@RequestBody LocationDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.saveOne(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('location:update', '_superAdmin')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateOne(@PathVariable Long id, @RequestBody LocationDto entity) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(locationService.updateOne(entity, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }

    @PreAuthorize("hasAnyAuthority('location:delete', '_superAdmin')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(locationService.deleteById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\":\"Error. Por favor intente más tarde.\"}");
        }
    }
}
