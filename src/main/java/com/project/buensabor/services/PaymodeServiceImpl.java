package com.project.buensabor.services;

import com.project.buensabor.entities.Paymode;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.PaymodeService;
import org.springframework.stereotype.Service;

@Service
public class PaymodeServiceImpl extends BaseServicesImpl<Paymode, Long> implements PaymodeService {

    public PaymodeServiceImpl(BaseRepository<Paymode, Long> baseRepository) {super(baseRepository);}
}
