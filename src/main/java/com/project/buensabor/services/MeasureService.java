package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.MeasureDto;
import com.project.buensabor.entities.Measure;
import com.project.buensabor.repositories.MeasureRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MeasureService {


    private final MeasureRepository measureRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public MeasureService(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    public Measure saveMeasure(Measure measure) {
        return measureRepository.save(measure);
    }

    public List<MeasureDto> getAllMeasurees() {
        List<MeasureDto> result = new ArrayList<>();
        List<Measure> list = measureRepository.findAll();

        for (Measure measure : list) {
            MeasureDto measureDto = MeasureDto.builder().measure(measure.getMeasure()).build();
            result.add(measureDto);
        }


        return result;
    }

    public MeasureDto getMeasure(Long measureID) {
        Optional<Measure> measure = measureRepository.findById(measureID);
        if(!(measure.isEmpty())) {
            return MeasureDto.builder().measure(measure.get().getMeasure()).build();

        }
        return null;
    }

    public Measure updateMeasure(Measure measure, Long measureID) {
        Optional<Measure> measureList = measureRepository.findById(measureID);
        if (measureList.get().getId_measure() == measure.getId_measure()) {
            return measureRepository.save(measure);
        } else {
            return null;
        }
    }

    public void deleteMeasureById(Long measureID) {
        measureRepository.deleteById(measureID);
    }
}
