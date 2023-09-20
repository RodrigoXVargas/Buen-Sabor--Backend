package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.ProductIngredientsMapper;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.ProductIngredientRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.ProductIngredientService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ProductIngredientServiceImpl extends BaseServicesDTOImpl<ProductIngredient, PIngredientDto, ProductIngredientsMapper, Long> implements ProductIngredientService {


    public ProductIngredientServiceImpl(BaseRepository<ProductIngredient, Long> baseRepository, ProductIngredientsMapper mapper) {
        super(baseRepository, mapper);
    }

    @Autowired
    private ProductIngredientRepository ingredientRepository;

    private ModelMapper modelMapper = new ModelMapper();



    @Override
    @Transactional
    public List<PIngredientsCantDto> ingredientsByProductId(Long id) throws Exception {
        try {
            List<ProductIngredient> entities = ingredientRepository.findProductIngredientsByProductId(id);
            List<PIngredientsCantDto> entitiesDtos = new ArrayList<>();
            if (!entities.isEmpty()){
                for (ProductIngredient entity: entities) {
                    entitiesDtos.add(modelMapper.map(entity, PIngredientsCantDto.class));
                }
            }
            return entitiesDtos;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
