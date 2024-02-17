package com.example.demo.services.orderService;

import com.example.demo.exceptions.ExceptionHandlers;
import com.example.demo.models.Orders.CreateOrderRequest;
import com.example.demo.models.Orders.OrderEntity;
import com.example.demo.models.Products.ProductEntity;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.repositories.interfaces.OrderRepoInterface;
import com.example.demo.repositories.interfaces.ProductRepoInterface;
import com.example.demo.repositories.interfaces.UsersRepoInterface;
import com.example.demo.services.EmailSender;
import com.example.demo.services.helpers.Utils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepoInterface orderRepoInterface;
    private final UsersRepoInterface usersRepoInterface;
    private final ProductRepoInterface productRepoInterface;
    private final EmailSender emailSender;
    public static final boolean ENABLE_EMAILS = false;

    public OrderEntity createNewOrder(CreateOrderRequest orderToCreate) {
        String orderCreatedAt = Utils.convertDateFormat(orderToCreate.getOrderDate());

        UserEntity user = usersRepoInterface.findById(orderToCreate.getUserID())
                .orElseThrow(() -> {
                    logger.error("User with ID {} not found", orderToCreate.getUserID());
                    return new ExceptionHandlers.UserNotFoundException("User with ID: " + orderToCreate.getUserID() + " not found!");
                });

        ProductEntity product = productRepoInterface.findById(orderToCreate.getProductID())
                .orElseThrow(() -> {
                    logger.error("Product with ID {} not found", orderToCreate.getProductID());
                    return new ExceptionHandlers.ProductNotFoundException("Product with ID: " + orderToCreate.getProductID() + " not found!");
                });

        if (orderToCreate.getTotalAmount() != orderToCreate.getQuantity() * product.getPrice()) {
            logger.error("{}'s price is: {}. Total amount is not correct.", product.getProductName(), product.getPrice());
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

        if(ENABLE_EMAILS){
            emailSender.sendEmail(user.getEmail(), "Order received", "Hi " + user.getUserName() +
                    ", We have received your order. Thanks!");
        }

        return createdOrder;
    }
}
