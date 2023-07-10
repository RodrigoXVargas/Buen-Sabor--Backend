package com.project.buensabor.services;

import com.project.buensabor.entities.StatusOrder;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.StatusService;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl extends BaseServicesImpl<StatusOrder, Long> implements StatusService {

    public StatusServiceImpl(BaseRepository<StatusOrder, Long> baseRepository) {
        super(baseRepository);
    }

}
