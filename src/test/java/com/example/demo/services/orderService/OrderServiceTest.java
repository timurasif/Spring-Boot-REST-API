package com.example.demo.services.orderService;

import com.example.demo.exceptions.ExceptionHandlers;
import com.example.demo.models.Orders.CreateOrderRequest;
import com.example.demo.models.Orders.OrderEntity;
import com.example.demo.models.Products.ProductEntity;
import com.example.demo.models.Users.UserEntity;
import com.example.demo.repositories.interfaces.OrderRepoInterface;
import com.example.demo.repositories.interfaces.ProductRepoInterface;
import com.example.demo.repositories.interfaces.UsersRepoInterface;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @MockBean
    private OrderRepoInterface orderRepoInterface;
    @MockBean
    private UsersRepoInterface usersRepoInterface;
    @MockBean
    private ProductRepoInterface productRepoInterface;

    @Test
    @DisplayName("Create New Order Test - Success")
    public void createNewOrderSuccessTest(){
        int userId = 5;
        int productId = 103;
        LocalDate date = LocalDate.of(2024, 01, 27);
        OrderEntity createdOrder = OrderEntity.builder()
                .orderID(999)
                .userId(userId)
                .productId(productId)
                .orderDate("20240127")
                .quantity(3)
                .totalAmount(59.97)
                .build();
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .userID(userId)
                .productID(productId)
                .orderDate(date)
                .quantity(3)
                .totalAmount(59.97)
                .build();

        when(usersRepoInterface.findById(userId)).thenReturn(Optional.of(new UserEntity(5, "Michael Brown", "michael@example.com", "890 Cedar Ave")));
        when(productRepoInterface.findById(productId)).thenReturn(Optional.of(new ProductEntity(103, "Product C", 19.99, "Description for Product C")));
        when(orderRepoInterface.saveAndFlush(Mockito.any(OrderEntity.class))).thenReturn(createdOrder);

        assertEquals(createdOrder.getOrderID(), orderService.createNewOrder(createOrderRequest).getOrderID());
        assertEquals(createdOrder.getProductId(), orderService.createNewOrder(createOrderRequest).getProductId());
        assertEquals(createdOrder.getOrderDate(), orderService.createNewOrder(createOrderRequest).getOrderDate());
        assertEquals(createdOrder.getQuantity(), orderService.createNewOrder(createOrderRequest).getQuantity());
        assertEquals(createdOrder.getTotalAmount(), orderService.createNewOrder(createOrderRequest).getTotalAmount());
    }

    @Test
    @DisplayName("Create New Order Test - User Not Found")
    public void createNewOrderUserNotFoundTest(){
        int userId = 100;
        int productId = 103;
        LocalDate date = LocalDate.of(2024, 01, 27);
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .userID(userId)
                .productID(productId)
                .orderDate(date)
                .quantity(3)
                .totalAmount(59.97)
                .build();

        when(usersRepoInterface.findById(userId)).thenReturn(Optional.empty());

        assertThrows(ExceptionHandlers.UserNotFoundException.class,
                () -> orderService.createNewOrder(createOrderRequest));
    }

    @Test
    @DisplayName("Create New Order Test - Product Not Found")
    public void createNewOrderProductNotFoundTest(){
        int userId = 5;
        int productId = 200;
        LocalDate date = LocalDate.of(2024, 01, 27);
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .userID(userId)
                .productID(productId)
                .orderDate(date)
                .quantity(3)
                .totalAmount(59.97)
                .build();

        when(usersRepoInterface.findById(userId)).thenReturn(Optional.of(new UserEntity(5, "Michael Brown", "michael@example.com", "890 Cedar Ave")));
        when(productRepoInterface.findById(productId)).thenReturn(Optional.empty());

        assertThrows(ExceptionHandlers.ProductNotFoundException.class,
                () -> orderService.createNewOrder(createOrderRequest));
    }

    @Test
    @DisplayName("Create New Order Test - Wrong price")
    public void createNewOrderWrongPriceTest(){
        int userId = 5;
        int productId = 103;
        LocalDate date = LocalDate.of(2024, 01, 27);
        CreateOrderRequest createOrderRequest = CreateOrderRequest.builder()
                .userID(userId)
                .productID(productId)
                .orderDate(date)
                .quantity(3)
                .totalAmount(60.0)
                .build();

        when(usersRepoInterface.findById(userId)).thenReturn(Optional.of(new UserEntity(5, "Michael Brown", "michael@example.com", "890 Cedar Ave")));
        when(productRepoInterface.findById(productId)).thenReturn(Optional.of(new ProductEntity(103, "Product C", 19.99, "Description for Product C")));

        assertThrows(ExceptionHandlers.WrongPriceException.class,
                () -> orderService.createNewOrder(createOrderRequest));
    }

}