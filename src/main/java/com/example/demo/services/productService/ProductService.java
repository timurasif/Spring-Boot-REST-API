package com.example.demo.services.productService;

import com.example.demo.exceptions.ExceptionHandlers;
import com.example.demo.models.Products.CreateProductRequest;
import com.example.demo.models.Products.ProductEntity;
import com.example.demo.models.TopThreeProductsDTO;
import com.example.demo.models.TopThreeReorderedProductsDTO;
import com.example.demo.repositories.interfaces.ProductRepoInterface;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    private final ProductRepoInterface productRepoInterface;

    public ProductEntity getById(Integer id) {
        Optional<ProductEntity> product = productRepoInterface.findById(id);
        if (product.isPresent()) {
            return product.get();
        } else {
            logger.error("Product with ID {} not found", id);
            throw new ExceptionHandlers.ProductNotFoundException("Product with ID " + id + " not found");
        }
    }

    public ProductEntity createProduct(CreateProductRequest createProductRequest) {
        ProductEntity productEntity = ProductEntity.builder()
                .productName(createProductRequest.getProductName())
                .description(createProductRequest.getDescription())
                .price(createProductRequest.getPrice())
                .build();
        return productRepoInterface.save(productEntity);
    }

    public void deleteProduct(Integer id) {
        if (productRepoInterface.existsById(id)) {
            productRepoInterface.deleteById(id);
            logger.info("Product with ID {} deleted successfully", id);
        } else {
            logger.error("Product with ID {} not found", id);
            throw new ExceptionHandlers.ProductNotFoundException("Product with ID " + id + " not found");
        }
    }

    public List<TopThreeProductsDTO> getTopThree(){
        return productRepoInterface.getTopThree();
    }

    public List<TopThreeReorderedProductsDTO> getTopThreeReordered(){
        return productRepoInterface.getTopThreeReordered();
    }

}
