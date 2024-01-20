package com.example.demo.controllers.Products;

import com.example.demo.models.TopThreeProductsDTO;
import com.example.demo.models.TopThreeReorderedProductsDTO;
import com.example.demo.services.productService.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {
    ProductService productService;

    @GetMapping("/get-top-three")
    @ResponseBody
    public List<TopThreeProductsDTO> getTopThree() {
        return productService.getTopThree();
    }

    @GetMapping("/get-top-three-reordered")
    @ResponseBody
    public List<TopThreeReorderedProductsDTO> getTopThreeReordered() {
        return productService.getTopThreeReordered();
    }
}
