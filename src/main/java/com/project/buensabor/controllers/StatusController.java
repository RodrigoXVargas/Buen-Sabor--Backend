package com.project.buensabor.controllers;

import com.project.buensabor.dto.orderDto.StatusDto;
import com.project.buensabor.entities.Status;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/status")
@CrossOrigin(origins = "*")
public class StatusController {
    private final StatusService statusService;

    @Autowired
    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllStatus()
    {
        List<StatusDto> list = statusService.getAllCategories();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public StatusDto getStatusByID(@PathVariable("id") Long statusID)
    {
        StatusDto status = statusService.getStatus(statusID);
        return status;
    }

    @PostMapping("/save")
    public Status saveStatus(@Valid @RequestBody Status status)
    {
        return statusService.saveStatus(status);
    }

    @PutMapping("/update/{id}")
    public Status updateStatus(@RequestBody Status status, @PathVariable("id") Long statusID)
    {
        return statusService.updateStatus(status, statusID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStatusById(@PathVariable("id") Long statusID)
    {
        statusService.deleteStatusById(statusID);
        return "Deleted Successfully";
    }
}
