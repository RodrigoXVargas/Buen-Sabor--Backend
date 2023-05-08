package com.project.buensabor.services;

import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Category;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.AddressService;
import com.project.buensabor.services.interfaces.CategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends BaseServicesImpl<Category, Long> implements CategoryService {

    public CategoryServiceImpl(BaseRepository<Category, Long> baseRepository) {
        super(baseRepository);
    }

}
