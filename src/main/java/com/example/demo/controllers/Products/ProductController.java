package com.example.demo.controllers.Products;

import com.example.demo.models.TopThreeProductsDTO;
import com.example.demo.models.TopThreeReorderedProductsDTO;
import com.example.demo.services.productService.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    @GetMapping("/get-top-three")
    @Operation(summary = "Get the top 3 most ordered products based on number of all orders.")
    @ResponseBody
    public List<TopThreeProductsDTO> getTopThree() {
        return productService.getTopThree();
    }

    @GetMapping("/get-top-three-reordered")
    @Operation(summary = "Get the top 3 most re-ordered products based on each user's first order.")
    @ResponseBody
    public List<TopThreeReorderedProductsDTO> getTopThreeReordered() {
        return productService.getTopThreeReordered();
    }
}
