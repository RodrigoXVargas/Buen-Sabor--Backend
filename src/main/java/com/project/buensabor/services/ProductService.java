package com.project.buensabor.services;

import com.project.buensabor.dto.productDto.ProductDto;
import com.project.buensabor.entities.Product;
import com.project.buensabor.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {


    private final ProductRepository productRepository;
    ModelMapper mapper = new ModelMapper();

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    public List<ProductDto> getAllCategories() {
        List<ProductDto> result = new ArrayList<>();
        List<Product> list = productRepository.findAll();

        for (Product product : list) {
            ProductDto productDto = ProductDto.builder().name(product.getName()).price(product.getPrice()).active(product.getActive()).subcategory(product.getSubcategory()).build();
            result.add(productDto);
        }
        return result;
    }

    public ProductDto getProduct(Long productID) {
        Optional<Product> product = productRepository.findById(productID);
        if(!(product.isEmpty())){
            return ProductDto.builder().name(product.get().getName()).price(product.get().getPrice()).active(product.get().getActive()).subcategory(product.get().getSubcategory()).build();
        }
        return null;
    }

    public Product updateProduct(Product product, Long productID) {
        Optional<Product> productList = productRepository.findById(productID);
        if (productList.get().getId_product() == product.getId_product()) {
            return productRepository.save(product);
        } else {
            return null;
        }
    }

    public void deleteProductById(Long productID) {
        productRepository.deleteById(productID);
    }
}
