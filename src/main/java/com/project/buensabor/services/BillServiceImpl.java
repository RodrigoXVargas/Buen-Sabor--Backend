package com.project.buensabor.services;

import com.project.buensabor.entities.Bill;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.BillService;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl extends BaseServicesImpl<Bill, Long> implements BillService {

    public BillServiceImpl(BaseRepository<Bill, Long> baseRepository) {
        super(baseRepository);
    }

}
