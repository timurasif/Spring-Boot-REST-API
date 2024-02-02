package com.example.demo.models.Orders;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;


import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    Integer userID;
    Integer productID;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    private Integer quantity;
    private Double totalAmount;

    @Override
    public String toString() {
        return "CreateOrderRequest{" +
                "userID=" + userID +
                ", productID='" + productID + '\'' +
                ", orderDate='" + orderDate + '\'' +
                ", quantity='" + quantity + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                '}';
    }
}
