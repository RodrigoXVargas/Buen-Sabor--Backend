package com.project.buensabor.services;

import com.project.buensabor.entities.Measure;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.MeasureService;
import org.springframework.stereotype.Service;

@Service
public class MeasureServiceImpl extends BaseServicesImpl<Measure, Long> implements MeasureService {

    public MeasureServiceImpl(BaseRepository<Measure, Long> baseRepository) {
        super(baseRepository);
    }

}
