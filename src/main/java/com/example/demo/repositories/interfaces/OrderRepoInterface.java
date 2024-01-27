package com.example.demo.repositories.interfaces;

import com.example.demo.models.Orders.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepoInterface extends JpaRepository<OrderEntity, Integer> {
}
