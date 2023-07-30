package com.project.buensabor.controllers;

import com.project.buensabor.controllers.Base.BaseControllerImpl;
import com.project.buensabor.dto.productDto.MeasureDto;
import com.project.buensabor.entities.Measure;
import com.project.buensabor.services.MeasureServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@CrossOrigin(origins = "*",allowedHeaders = "*")
@RequestMapping(path = "/api/measures")
public class MeasureController extends BaseControllerImpl<Measure, MeasureDto, MeasureServiceImpl> {
}
