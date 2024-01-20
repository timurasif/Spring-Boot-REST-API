package com.example.demo.models.Orders;

import com.example.demo.models.Users.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity(name = "Orders")
@Getter
@Setter
@AllArgsConstructor
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    Integer orderID;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity userId;

    //Schema provided only has 1 product per order
    @ManyToOne
    @JoinColumn(name = "product_id")
    private UserEntity productId;
    @Column(name = "order_date")
    private Date orderDate;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "total_amount")
    private Double totalAmount;
}
