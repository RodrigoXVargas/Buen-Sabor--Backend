package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.StatusOrderMapper;
import com.project.buensabor.dto.orderDto.StatusOrderDto;
import com.project.buensabor.entities.StatusOrder;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.StatusRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.StatusService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class StatusServiceImpl extends BaseServicesDTOImpl<StatusOrder, StatusOrderDto, StatusOrderMapper, Long> implements StatusService {


    public StatusServiceImpl(BaseRepository<StatusOrder, Long> baseRepository, StatusOrderMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public StatusOrderDto getStatusOrder(String status) throws CustomException {
        try{
            Optional<StatusOrder> statusOrderOptional = statusRepository.findByStatusType(status);
            StatusOrder statusOrder = statusOrderOptional.get();
            StatusOrderDto statusOrderDto = mapper.convertToDto(statusOrder);
            return statusOrderDto;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }
}
