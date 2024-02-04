package com.example.demo.controllers.Orders;

import com.example.demo.controllers.Products.ProductController;
import com.example.demo.models.Orders.CreateOrderRequest;
import com.example.demo.models.Orders.OrderEntity;
import com.example.demo.models.Response;
import com.example.demo.services.orderService.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "Create new order.")
    public Response<Object> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        logger.info("Create order request: {}", createOrderRequest);
        OrderEntity createdOrder = orderService.createNewOrder(createOrderRequest);
        logger.info("Created order: {}", createdOrder);
        return new Response(201, "Order created successfully.", createdOrder);
    }
}
