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
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderService {

    private final OrderRepoInterface orderRepoInterface;
    private final UsersRepoInterface usersRepoInterface;
    private final ProductRepoInterface productRepoInterface;

    public OrderEntity createNewOrder(CreateOrderRequest orderToCreate){

        String orderCreatedAt = Utils.convertDateFormat(orderToCreate.getOrderDate());

        UserEntity user = usersRepoInterface.findById(orderToCreate.getUserID())
                .orElseThrow(() -> new ExceptionHandlers.UserNotFoundException("User with ID: " + orderToCreate.getUserID() + " not found!"));

        ProductEntity product = productRepoInterface.findById(orderToCreate.getProductID())
                .orElseThrow(() -> new ExceptionHandlers.ProductNotFoundException("Product with ID: " + orderToCreate.getProductID() + " not found!"));

        if(orderToCreate.getTotalAmount() != orderToCreate.getQuantity()*product.getPrice()){
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
