package com.project.buensabor.services;

import com.project.buensabor.entities.Address;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.AddressService;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl extends BaseServicesImpl<Address, Long> implements AddressService {

    public AddressServiceImpl(BaseRepository<Address, Long> baseRepository) {
        super(baseRepository);
    }

}
