package com.project.buensabor.controllers.Base;

import com.project.buensabor.dto.BaseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.Serializable;

public interface BaseController<F extends BaseDto, ID extends Serializable> {


    public ResponseEntity<?> getAll();

    public ResponseEntity<?> getOne(@PathVariable ID id);


}
