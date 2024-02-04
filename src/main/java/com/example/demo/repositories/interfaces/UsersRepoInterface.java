package com.example.demo.repositories.interfaces;

import com.example.demo.models.TopThreeUsersDTO;
import com.example.demo.models.Users.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepoInterface extends JpaRepository<UserEntity, Integer> {

//    Get the top 3 users who ordered the most products based on the number of all products in the orders.

    @Query(value = "SELECT users.user_id, users.user_name, sum(orders.quantity) as num_products_ordered\n" +
            "FROM algotest.users\n" +
            "JOIN Orders on users.user_id=orders.user_id\n" +
            "group by users.user_id\n" +
            "Order by num_products_ordered Desc\n" +
            "Limit 3;", nativeQuery = true)
    List<TopThreeUsersDTO> getTopThree();
}
