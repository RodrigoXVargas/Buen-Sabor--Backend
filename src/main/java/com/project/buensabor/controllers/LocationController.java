package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.entities.Location;
import com.project.buensabor.services.LocationServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path = "/api/locations")
public class LocationController extends BaseControllerImpl<Location, LocationServiceImpl> {
}
