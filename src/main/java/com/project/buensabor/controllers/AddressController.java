package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.entities.Address;
import com.project.buensabor.services.AddressServiceImpl;
import com.project.buensabor.services.interfaces.AddressService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path = "/api/addresses")
public class AddressController extends BaseControllerImpl<Address, AddressServiceImpl> {
}
