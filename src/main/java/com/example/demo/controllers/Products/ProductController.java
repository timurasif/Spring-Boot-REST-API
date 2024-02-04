package com.example.demo.controllers.Products;

import com.example.demo.controllers.Users.UserController;
import com.example.demo.models.Products.CreateProductRequest;
import com.example.demo.models.Products.ProductEntity;
import com.example.demo.models.Response;
import com.example.demo.models.TopThreeProductsDTO;
import com.example.demo.models.TopThreeReorderedProductsDTO;
import com.example.demo.models.Users.CreateUserRequest;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.services.productService.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@Validated
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    ProductService productService;

    @GetMapping("/get")
    @Operation(summary = "Get product by id.")
    public Response<Object> getById(@RequestParam Integer id){
        logger.info("Get Product request with user Id: {}", id);
        ProductEntity product = productService.getById(id);
        logger.info("Get User response: {}", product);
        return new Response(200, "Success.", product);
    }

    @PostMapping("/create")
    @Operation(summary = "Create product.")
    public Response<Object> create(@Valid @RequestBody CreateProductRequest createProductRequest){
        logger.info("Get Product request with: {}", createProductRequest);
        ProductEntity createProduct = productService.createProduct(createProductRequest);
        logger.info("Product created: {}", createProduct);
        return new Response(201, "Product created successfully.", createProduct);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "Delete product by id.")
    public Response<Object> deleteById(@RequestParam Integer id){
        logger.info("Delete Product request with Product Id: {}", id);
        productService.deleteProduct(id);
        return new Response(200, "Success.", null);
    }

    @GetMapping("/get-top-three")
    @Operation(summary = "Get the top 3 most ordered products based on number of all orders.")
    public Response<Object> getTopThree() {
        logger.info("Get Top 3 product request.");
        List<TopThreeProductsDTO> list = productService.getTopThree();
        return new Response(200, "Success.", list);
    }

    @GetMapping("/get-top-three-reordered")
    @Operation(summary = "Get the top 3 most re-ordered products based on each user's first order.")
    public Response<Object> getTopThreeReordered() {
        logger.info("Get Top 3 reordered product request.");
        List<TopThreeReorderedProductsDTO> list = productService.getTopThreeReordered();
        return new Response(200, "Success.", list);
    }
}
