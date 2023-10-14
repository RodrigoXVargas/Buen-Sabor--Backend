package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.PaymodeMapper;
import com.project.buensabor.dto.orderDto.PaymodeDto;
import com.project.buensabor.entities.Paymode;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.PaymodeService;
import org.springframework.stereotype.Service;

@Service
public class PaymodeServiceImpl extends BaseServicesDTOImpl<Paymode, PaymodeDto, PaymodeMapper, Long> implements PaymodeService {


    public PaymodeServiceImpl(BaseRepository<Paymode, Long> baseRepository, PaymodeMapper mapper) {
        super(baseRepository, mapper);
    }
}
