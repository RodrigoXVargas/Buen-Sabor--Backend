package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.ProductMapper;
import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.entities.Ingredient;
import com.project.buensabor.entities.Product;
import com.project.buensabor.entities.ProductIngredient;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.ProductIngredientRepository;
import com.project.buensabor.repositories.ProductRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.ProductIngredientService;
import com.project.buensabor.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class ProductServiceImpl extends BaseServicesDTOImpl<Product, ProductDto, ProductMapper, Long> implements ProductService {

    @Autowired
    private ProductIngredientRepository productIngredientRepository;

    @Autowired
    private ProductIngredientService productIngredientService;

    @Autowired
    private ProductRepository productRepository;

    private ModelMapper modelMapper = new ModelMapper();

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
            entityDto.setIngredients(productIngredientService.ingredientsByProductId(entityDto.getId()));
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

    @Override
    public String changeActive(Long id) throws Exception {
        try{
            Optional<Product> optionalProduct = productRepository.findById(id);
            Product product = optionalProduct.get();
            product.setActive(!product.isActive());
            product = productRepository.save(product);
            return "Se cambio el estado a "+ product.isActive();
        }catch (Exception e){
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    private List<ProductDto> getProductDtos(List<Product> entities) throws Exception {
        List<ProductDto> entitiesDtos = new ArrayList<>();
        for (Product entity : entities) {
            ProductDto entityDto = mapper.convertToDto(entity);
            entityDto.setIngredients(productIngredientService.ingredientsByProductId(entityDto.getId()));
            entitiesDtos.add(entityDto);
        }
        return entitiesDtos;
    }

    @Override
    @Transactional
    public ProductDto saveOne(ProductDto entityDto) throws Exception {
        try {
            Product product = new Product();
            modelMapper.map(entityDto, product);
            product = productRepository.save(product);

            List<PIngredientsCantDto> productIngredientList = new ArrayList<>();

            if(entityDto.getIngredients().size() != 0){
                for (PIngredientsCantDto pIngredientsCantDto : entityDto.getIngredients()) {
                    ProductIngredient productIngredient = new ProductIngredient();
                    productIngredient.setProduct(product);
                    productIngredient.setIngredient(modelMapper.map(pIngredientsCantDto.getIngredient(), Ingredient.class));
                    productIngredient.setCant(pIngredientsCantDto.getCant());
                    productIngredient = productIngredientRepository.save(productIngredient);
                    modelMapper.map(productIngredient, pIngredientsCantDto);
                    productIngredientList.add(pIngredientsCantDto);
                }
            }
            entityDto = mapper.convertToDto(product);
            entityDto.setIngredients(productIngredientList);
            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ProductDto updateOne(ProductDto entity, Long id) throws Exception {
        try {
            Optional<Product> productOptional = productRepository.findById(id);
            Product productExistente = productOptional.get();
            modelMapper.map(entity, productExistente);
            productExistente = productRepository.save(productExistente);

            List<PIngredientsCantDto> productIngredientList = new ArrayList<>();

            if(entity.getIngredients().size() == 0){
                List<ProductIngredient> ingredientsCantDtoList = productIngredientRepository.findProductIngredientsByProductId(id);
                if (ingredientsCantDtoList.size() != 0) {
                    for (ProductIngredient productIngredient: ingredientsCantDtoList) {
                        productIngredientRepository.deleteById(productIngredient.getId());
                    }
                }
            } else {
                List<ProductIngredient> ingredientsCantDtoList = productIngredientRepository.findProductIngredientsByProductId(id);
                if (ingredientsCantDtoList.size() != 0) {
                    for (ProductIngredient productIngredient: ingredientsCantDtoList) {
                        long idBuscado = 0;
                        for (PIngredientsCantDto pIngredientsCantDto : entity.getIngredients()) {
                            if (Objects.nonNull(pIngredientsCantDto.getId())) {
                                if (pIngredientsCantDto.getId() == productIngredient.getId()){
                                    idBuscado = pIngredientsCantDto.getId();
                                }
                            }
                        }
                        if(idBuscado==0){
                            productIngredientRepository.deleteById(productIngredient.getId());
                        }
                    }
                }
                for (PIngredientsCantDto pIngredientsCantDto : entity.getIngredients()) {
                    ProductIngredient productIngredient;
                    if(Objects.isNull(pIngredientsCantDto.getId())){
                        productIngredient = new ProductIngredient();
                    } else {
                        Optional<ProductIngredient> productIngredientOptional = productIngredientRepository.findById(pIngredientsCantDto.getId());
                        productIngredient = productIngredientOptional.get();
                    }
                    productIngredient.setProduct(productExistente);
                    productIngredient.setIngredient(modelMapper.map(pIngredientsCantDto.getIngredient(), Ingredient.class));
                    productIngredient.setCant(pIngredientsCantDto.getCant());
                    productIngredient = productIngredientRepository.save(productIngredient);
                    modelMapper.map(productIngredient, pIngredientsCantDto);
                    productIngredientList.add(pIngredientsCantDto);
                }
            }
            entity = mapper.convertToDto(productExistente);
            entity.setIngredients(productIngredientList);
            return entity;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws Exception {
        try {
            if (productRepository.existsById(id)) {

                List<ProductIngredient> ingredientList = productIngredientRepository.findProductIngredientsByProductId(id);
                for (ProductIngredient productIngredient: ingredientList) {
                    productIngredientRepository.deleteById(productIngredient.getId());
                }
                productRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

}
