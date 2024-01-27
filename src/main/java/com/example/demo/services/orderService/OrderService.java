package com.example.demo.services.orderService;

import com.example.demo.exceptions.ExceptionHandlers;
import com.example.demo.models.Orders.CreateOrderRequest;
import com.example.demo.models.Orders.OrderEntity;
import com.example.demo.models.Products.ProductEntity;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.repositories.interfaces.OrderRepoInterface;
import com.example.demo.repositories.interfaces.ProductRepoInterface;
import com.example.demo.repositories.interfaces.UsersRepoInterface;
import com.example.demo.services.helpers.Utils;
import lombok.AllArgsConstructor;
import org.hibernate.query.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OrderService {

    private final OrderRepoInterface orderRepoInterface;
    private final UsersRepoInterface usersRepoInterface;
    private final ProductRepoInterface productRepoInterface;

    public OrderEntity createNewOrder(CreateOrderRequest orderToCreate){
        System.out.println("User Id: " + orderToCreate.getUserID());
        System.out.println("Product Id: " + orderToCreate.getProductID());
        System.out.println("Created at: " + orderToCreate.getOrderDate());

        String orderCreatedAt = Utils.convertDateFormat(orderToCreate.getOrderDate());

        System.out.println("Updated Created at: " + orderCreatedAt);

        Optional<UserEntity> userOptional = usersRepoInterface.findById(orderToCreate.getUserID());
        if(userOptional.isEmpty()){
            throw new ExceptionHandlers.UserNotFoundException("User with ID: " + orderToCreate.getUserID() + " not found!");
        }
        UserEntity user = userOptional.get();
        System.out.println("User name: " + user.getUserName());

        Optional<ProductEntity> productOptional = productRepoInterface.findById(orderToCreate.getProductID());
        if(productOptional.isEmpty()){
            throw new ExceptionHandlers.ProductNotFoundException("Product with ID: " + orderToCreate.getProductID() + " not found!");
        }
        ProductEntity product = productOptional.get();
        System.out.println("Product name: " + product.getProductName());

        if(orderToCreate.getTotalAmount() != orderToCreate.getQuantity()*product.getPrice()){
            System.out.println("Wrong amount...");
            throw new ExceptionHandlers.WrongPriceException(product.getProductName() + "'s price is: " + product.getPrice() + ". Total amount is not correct.");
        }
        OrderEntity orderEntity = OrderEntity.builder()
                .userId(orderToCreate.getUserID())
                .productId(orderToCreate.getProductID())
                .orderDate(orderCreatedAt)
                .quantity(orderToCreate.getQuantity())
                .totalAmount(orderToCreate.getTotalAmount())
                .build();

        OrderEntity createdOrder = orderRepoInterface.saveAndFlush(orderEntity);
        createdOrder.setUser(user);
        createdOrder.setProduct(product);

        return createdOrder;
    }
}
