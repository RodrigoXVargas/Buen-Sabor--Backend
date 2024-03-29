package com.project.buensabor.services;

import com.project.buensabor.ModelMappers.ProductMapper;
import com.project.buensabor.dto.orderDto.OrderProductsDtos.OProductsWithoutOrderDto;
import com.project.buensabor.dto.productDto.IngredientDto;
import com.project.buensabor.dto.productDto.ProductDtos.ProductDto;
import com.project.buensabor.dto.productDto.ProductDtos.ProductRanking;
import com.project.buensabor.dto.productDto.ProductDtos.ProductRankingDto;
import com.project.buensabor.dto.productDto.ProductIngredientDTOs.PIngredientsCantDto;
import com.project.buensabor.entities.*;
import com.project.buensabor.enums.TypeMovement;
import com.project.buensabor.exceptions.CustomException;
import com.project.buensabor.repositories.Base.BaseRepository;
import com.project.buensabor.repositories.CategoryRepository;
import com.project.buensabor.repositories.MovementRepository;
import com.project.buensabor.repositories.ProductIngredientRepository;
import com.project.buensabor.repositories.ProductRepository;
import com.project.buensabor.services.Base.BaseServicesDTOImpl;
import com.project.buensabor.services.interfaces.IngredientService;
import com.project.buensabor.services.interfaces.ProductIngredientService;
import com.project.buensabor.services.interfaces.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@Slf4j
public class ProductServiceImpl extends BaseServicesDTOImpl<Product, ProductDto, ProductMapper, Long> implements ProductService {

    @Autowired
    private IngredientService ingredientService;

    @Autowired
    private ProductIngredientRepository productIngredientRepository;

    @Autowired
    private ProductIngredientService productIngredientService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private ImageService imageService;

    @Autowired
    private DateService dateService;

    @Autowired
    private MovementRepository movementRepository;

    private static final String CLOUDINARY_FOLDER = "products";

    public ProductServiceImpl(BaseRepository<Product, Long> baseRepository, ProductMapper mapper) {
        super(baseRepository, mapper);
    }

    @Override
    @Transactional //Indica que el método es una transacción.
    public List<ProductDto> findAll() throws CustomException {
        try {
            List<Product> entities = productRepository.findAll();
            return getProductDtos(entities);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }


    @Override
    @Transactional
    public ProductDto findById(Long id) throws CustomException {
        try {
            Optional<Product> entityOptional = baseRepository.findById(id);
            ProductDto entityDto = mapper.convertToDto(entityOptional.get());
            entityDto.setIngredients(productIngredientService.ingredientsByProductId(entityDto.getId()));
            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public List<ProductDto> findProductsActive() throws CustomException {
        try {
            List<Product> entities = productRepository.findAllByActive();
            return getProductDtos(entities);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public String changeActive(Long id) throws CustomException {
        try{
            Optional<Product> optionalProduct = productRepository.findById(id);
            Product product = optionalProduct.get();
            product.setActive(!product.isActive());
            product = productRepository.save(product);
            return "Se cambio el estado a "+ product.isActive();
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional //Indica que el método es una transacción.
    public List<ProductDto> findProductsByQDesc() throws CustomException {
        try {
            List<Product> entities = productRepository.getProductsOrderByQuantitySoldDesc();
            return getProductDtos(entities);
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    public List<PIngredientsCantDto> extraerIngredientes(List<OProductsWithoutOrderDto> productosAValidar) throws CustomException {
        try{
            List<PIngredientsCantDto> ingredientesTotales = new ArrayList<>();
            List<IngredientDto> ingredientList = ingredientService.findAll();
            for (IngredientDto ingredient: ingredientList) {
                ingredient.setStock(0L);
            }

            for (OProductsWithoutOrderDto product: productosAValidar) {
                List<PIngredientsCantDto> ingredientsProduct = productIngredientService.ingredientsByProductId(product.getProduct().getId());
                if(ingredientsProduct.size()!=0){
                    for (PIngredientsCantDto ingredientsCant : ingredientsProduct) {
                        for (IngredientDto ingredient: ingredientList) {
                            if (ingredient.getId()== ingredientsCant.getIngredient().getId()){
                                ingredient.setStock(ingredient.getStock() + (ingredientsCant.getCant()*product.getCant()));
                                break;
                            }
                        }
                    }
                }
            }

            for (IngredientDto ingredient: ingredientList) {
                if(ingredient.getStock()!=0){
                    ingredientesTotales.add(new PIngredientsCantDto(ingredient, ingredient.getStock()));
                }
            }

            return ingredientesTotales;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }

    }


    @Override
    public boolean validarStock(List<OProductsWithoutOrderDto> productosAValidar) throws CustomException {
        try{
            boolean bandera = true;
            List<PIngredientsCantDto> ingredientList = extraerIngredientes(productosAValidar);

            for (PIngredientsCantDto ingredient: ingredientList) {
                if (ingredientService.validarStock(ingredient.getIngredient().getId(), ingredient.getCant())){
                    //System.out.println("No hay stock para "+ ingredient.getName());

                } else {
                    //System.out.println("si hay stock para "+ ingredient.getName());
                    bandera = false;
                    break;
                }


            }
            return bandera;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public void descontarOReponerStock(List<OProductsWithoutOrderDto> productosAValidar, boolean descontarOReponer) throws CustomException {
        try {
            List<PIngredientsCantDto> ingredientList = extraerIngredientes(productosAValidar);
            ingredientService.descontarOReponerStock(ingredientList, descontarOReponer);



        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    public List<ProductRankingDto> getBestSellingProducts(String desde, String hasta) throws CustomException {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate desdeLD = LocalDate.parse(desde, formatter);
            LocalDate hastaLD = LocalDate.parse(hasta, formatter);
            List<ProductRanking> objects = productRepository.rankingProductsByDates(desdeLD, hastaLD);
            List<ProductRankingDto> productRankingDtoList = new ArrayList<>();
            for (ProductRanking object: objects) {
                Optional<Category> optionalCategory = categoryRepository.findById(object.getSubcategory_fk().longValue());
                Category category = optionalCategory.get();
                ProductRankingDto productRankingDto = new ProductRankingDto(
                        object.getName(),
                        object.getActive(),
                        category,
                        object.getCost().doubleValue(),
                        object.getPrice().doubleValue(),
                        object.getQuantity_sold().longValue(),
                        object.getTotal_cost().doubleValue(),
                        object.getTotal_profit().doubleValue()
                );
                productRankingDto.setId(object.getId().longValue());
                productRankingDtoList.add(productRankingDto);
            }

            return productRankingDtoList;
        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
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


    @Transactional
    public ProductDto saveOne(ProductDto entityDto, MultipartFile image) throws CustomException {
        try {
            if(image.isEmpty()){
                throw new CustomException("Imagen requerida");
            }

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
            Map<String, Object> uploadData = imageService.uploadImage(image, product.getId(), CLOUDINARY_FOLDER);
            product.setImage((String) uploadData.get("url"));

            if (!product.getSubcategory().getParentCategory().getName().equals("Bebidas")){
                product.setCost(this.getProductCost(product.getId()));
            }

            product = productRepository.save(product);

            entityDto = mapper.convertToDto(product);
            entityDto.setIngredients(productIngredientList);
            return entityDto;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }


    @Transactional
    public ProductDto updateOne(ProductDto entity, Long id, MultipartFile image) throws CustomException {
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
            if (Objects.nonNull(image) && !image.isEmpty()){
                Map<String, Object> uploadData = imageService.uploadImage(image, productExistente.getId(), CLOUDINARY_FOLDER);
                productExistente.setImage((String) uploadData.get("url"));

            }
            if (!(productExistente.getSubcategory().getParentCategory().getName().equals("Bebidas"))){
                productExistente.setCost(this.getProductCost(productExistente.getId()));
            }

            productExistente = productRepository.save(productExistente);

            entity = mapper.convertToDto(productExistente);
            entity.setIngredients(productIngredientList);
            return entity;
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean deleteById(Long id) throws CustomException {
        try {
            if (productRepository.existsById(id)) {

                List<ProductIngredient> ingredientList = productIngredientRepository.findProductIngredientsByProductId(id);
                for (ProductIngredient productIngredient: ingredientList) {
                    productIngredientRepository.deleteById(productIngredient.getId());
                }
                productRepository.deleteById(id);
                imageService.deleteImage(id, CLOUDINARY_FOLDER);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }


    public double getProductCost(Long idProduct) throws CustomException{
        try{
            double costProduct = 0;
            List<ProductIngredient> ingredientList = productIngredientRepository.findProductIngredientsByProductId(idProduct);
            for (ProductIngredient productIngredient: ingredientList) {
                costProduct+= productIngredient.getIngredient().getCost() * productIngredient.getCant();
            }
            return costProduct;

        }catch (Exception e){
            log.info(e.getMessage());
            throw new CustomException(e.getMessage());
        }
    }
}
