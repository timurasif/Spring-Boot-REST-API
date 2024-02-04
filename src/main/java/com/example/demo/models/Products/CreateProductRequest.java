package com.example.demo.models.Products;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductRequest {
    @NotBlank(message = "Product name is required")
    @Column(name = "product_name")
    private String productName;
    @NotNull(message = "Price is required")
    @Column(name = "price")
    private Double price;
    @NotBlank(message = "Description is required")
    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                ", productName='" + productName + '\'' +
                ", price='" + price + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
