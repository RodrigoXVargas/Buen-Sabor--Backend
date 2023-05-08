package com.project.buensabor.contmeasurelers;

import com.project.buensabor.dto.productDto.MeasureDto;
import com.project.buensabor.entities.Measure;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/measure")
public class MeasureController {
    private final MeasureService measureService;

    @Autowired
    public MeasureController(MeasureService measureService) {
        this.measureService = measureService;
    }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAllMeasure()
    {
        List<MeasureDto> list = measureService.getAllMeasurees();
        ResponseEntity<?> responseEntity = ResponseEntity.ok(list) ;
        return responseEntity;
    }

    @GetMapping("/get/{id}")
    public MeasureDto getMeasureByID(@PathVariable("id") Long measureID)
    {
        MeasureDto measure = measureService.getMeasure(measureID);
        return measure;
    }

    @PostMapping("/save")
    public Measure saveMeasure(@Valid @RequestBody Measure measure)
    {
        return measureService.saveMeasure(measure);
    }

    @PutMapping("/update/{id}")
    public Measure updateMeasure(@RequestBody Measure measure, @PathVariable("id") Long measureID)
    {
        return measureService.updateMeasure(measure, measureID);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteMeasureById(@PathVariable("id") Long measureID)
    {
        measureService.deleteMeasureById(measureID);
        return "Deleted Successfully";
    }
}
