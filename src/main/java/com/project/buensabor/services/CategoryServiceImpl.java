package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.CategoryDto;
import com.project.buensabor.entities.Address;
import com.project.buensabor.entities.Category;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.CategoryRepository;
import com.project.buensabor.services.Base.BaseServicesImpl;
import com.project.buensabor.services.interfaces.AddressService;
import com.project.buensabor.services.interfaces.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CategoryServiceImpl extends BaseServicesImpl<Category, Long> implements CategoryService {

    public CategoryServiceImpl(BaseRepository<Category, Long> baseRepository) {
        super(baseRepository);
    }

    @Autowired
    private CategoryRepository categoryRepository;

/*
    @Override
    public CategoryDto findById(Long id) throws Exception {
        try {
            Optional<CategoryDto> entityOptional = categoryRepository.findById(
                    id); //Optional porque no se sabe si se encontrará un registro que tenga el ID especificado como PrimaryKey.
            return entityOptional.get();
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
  */  
    /*@Override
    public Optional<Category> findByName(String name) throws Exception {
        return categoryRepository.findByName(name);
    }

    @Override
    public boolean existByName(String name) throws Exception {
        try{
            if (categoryRepository.existByName(name)){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }*/
}
