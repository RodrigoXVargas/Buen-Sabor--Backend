package com.project.buensabor.services;

import com.project.buensabor.entities.Section;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.SectionService;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl extends BaseServicesImpl<Section, Long> implements SectionService {

    public SectionServiceImpl(BaseRepository<Section, Long> baseRepository) {
        super(baseRepository);
    }

}
