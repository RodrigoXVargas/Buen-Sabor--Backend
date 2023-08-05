package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.StatusOrderMapper;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.entities.StatusOrder;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.StatusService;
import org.springframework.stereotype.Service;

@Service
public class StatusServiceImpl extends BaseServicesDTOImpl<StatusOrder, StatusOrderDto, StatusOrderMapper, Long> implements StatusService {


    public StatusServiceImpl(BaseRepository<StatusOrder, Long> baseRepository, StatusOrderMapper mapper) {
        super(baseRepository, mapper);
    }
}
