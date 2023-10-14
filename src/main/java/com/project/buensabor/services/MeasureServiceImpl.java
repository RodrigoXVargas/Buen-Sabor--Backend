package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.MeasureMapper;
import com.project.buensabor.dto.productDto.MeasureDto;
import com.project.buensabor.entities.Measure;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.MeasureService;
import org.springframework.stereotype.Service;

@Service
public class MeasureServiceImpl extends BaseServicesDTOImpl<Measure, MeasureDto, MeasureMapper, Long> implements MeasureService {


    public MeasureServiceImpl(BaseRepository<Measure, Long> baseRepository, MeasureMapper mapper) {
        super(baseRepository, mapper);
    }
}
