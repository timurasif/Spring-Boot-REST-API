package com.example.demo.controllers.Orders;

import com.example.demo.models.Orders.CreateOrderRequest;
import com.example.demo.models.Orders.OrderEntity;
import com.example.demo.models.Response;
import com.example.demo.services.orderService.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/orders")
@AllArgsConstructor
public class OrderController {

    OrderService orderService;

    @PostMapping("/create")
    @Operation(summary = "Create new order.")
    @ResponseBody
    public Response<Object> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        OrderEntity createdOrder = orderService.createNewOrder(createOrderRequest);
        return new Response(200, "Order created successfully.", createdOrder);
    }
}
