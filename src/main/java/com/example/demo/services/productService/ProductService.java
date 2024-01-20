package com.example.demo.services.productService;

import com.example.demo.models.TopThreeProductsDTO;
import com.example.demo.models.TopThreeReorderedProductsDTO;
import com.example.demo.repositories.interfaces.ProductRepoInterface;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class ProductService {
    private final ProductRepoInterface productRepoInterface;

    public List<TopThreeProductsDTO> getTopThree(){
        return productRepoInterface.getTopThree();
    }

    public List<TopThreeReorderedProductsDTO> getTopThreeReordered(){
        return productRepoInterface.getTopThreeReordered();
    }

}
