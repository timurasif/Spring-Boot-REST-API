package com.example.demo.controllers.Products;

import com.example.demo.controllers.Users.UserController;
import com.example.demo.models.TopThreeProductsDTO;
import com.example.demo.models.TopThreeReorderedProductsDTO;
import com.example.demo.services.productService.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    ProductService productService;

    @GetMapping("/get-top-three")
    @Operation(summary = "Get the top 3 most ordered products based on number of all orders.")
    public List<TopThreeProductsDTO> getTopThree() {
        logger.info("Get Top 3 product request.");
        return productService.getTopThree();
    }

    @GetMapping("/get-top-three-reordered")
    @Operation(summary = "Get the top 3 most re-ordered products based on each user's first order.")
    public List<TopThreeReorderedProductsDTO> getTopThreeReordered() {
        logger.info("Get Top 3 reordered product request.");
        return productService.getTopThreeReordered();
    }
}
