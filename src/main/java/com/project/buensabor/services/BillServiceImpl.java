package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.BillMapper;
import com.project.buensabor.dto.orderDto.BillDto;
import com.project.buensabor.entities.Bill;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.BillService;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl extends BaseServicesDTOImpl<Bill, BillDto, BillMapper, Long> implements BillService {


    public BillServiceImpl(BaseRepository<Bill, Long> baseRepository, BillMapper mapper) {
        super(baseRepository, mapper);
    }
}
