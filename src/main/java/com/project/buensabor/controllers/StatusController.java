package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.entities.StatusOrder;
import com.project.buensabor.services.StatusServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path = "/api/statusorder")
public class StatusController extends BaseControllerImpl<StatusOrder, StatusOrderDto, StatusServiceImpl> {
}
