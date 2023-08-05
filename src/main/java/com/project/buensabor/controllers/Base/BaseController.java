package com.project.buensabor.controllers.Base;

import com.project.buensabor.dto.BaseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;

public interface BaseController<F extends BaseDto, ID extends Serializable> {


    public ResponseEntity<?> getAll();

    public ResponseEntity<?> getOne(@PathVariable ID id);

    public ResponseEntity<?> saveOne(@RequestBody F entity) throws Exception;

    public ResponseEntity<?> updateOne(@PathVariable ID id, @RequestBody F entity);

    public ResponseEntity<?> deleteById(@PathVariable ID id);

}
