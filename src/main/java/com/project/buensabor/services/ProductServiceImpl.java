package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.ProductMapper;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.ProductRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.ProductIngredientService;
import com.project.buensabor.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl extends BaseServicesDTOImpl<Product, ProductDto, ProductMapper, Long> implements ProductService {


    @Autowired
    private ProductIngredientService ingredientService;
    @Autowired
    private ProductRepository productRepository;

    public ProductServiceImpl(BaseRepository<Product, Long> baseRepository, ProductMapper mapper) {
        super(baseRepository, mapper);
    }

    @Override
    @Transactional //Indica que el método es una transacción.
    public List<ProductDto> findAll() throws Exception {
        try {
            List<Product> entities = productRepository.findAll();
            return getProductDtos(entities);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    @Override
    @Transactional
    public ProductDto findById(Long id) throws Exception {
        try {
            Optional<Product> entityOptional = baseRepository.findById(id);
            ProductDto entityDto = mapper.convertToDto(entityOptional.get());
            entityDto.setIngredients(ingredientService.ingredientsByProductId(entityDto.getId()));
            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<ProductDto> findProductsActive() throws Exception {
        try {
            List<Product> entities = productRepository.findAllByActive();
            return getProductDtos(entities);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    private List<ProductDto> getProductDtos(List<Product> entities) throws Exception {
        List<ProductDto> entitiesDtos = new ArrayList<>();
        for (Product entity : entities) {
            ProductDto entityDto = mapper.convertToDto(entity);
            entityDto.setIngredients(ingredientService.ingredientsByProductId(entityDto.getId()));
            entitiesDtos.add(entityDto);
        }
        return entitiesDtos;
    }
}
