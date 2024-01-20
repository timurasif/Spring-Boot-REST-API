package com.example.demo.repositories.interfaces;

import com.example.demo.models.Products.ProductEntity;
import com.example.demo.models.TopThreeProductsDTO;
import com.example.demo.models.TopThreeReorderedProductsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepoInterface extends JpaRepository<ProductEntity, Integer> {

//    Get the top 3 most ordered products based on number of all orders.

    /* The term most ordered products is slightly confusing. It can be based on the quantity
    of that product per order or number of orders it was ordered in. Here I'm assuming
    the later.*/
    @Query(value = "SELECT p.product_id, p.product_name, count(o.product_id) as count\n" +
            "FROM products as p\n" +
            "Join orders as o on o.product_id=p.product_id\n" +
            "GROUP BY product_id\n" +
            "Order by count Desc\n" +
            "LIMIT 3;", nativeQuery = true)
    List<TopThreeProductsDTO> getTopThree();


//    Get the top 3 most re-ordered products based on each user's first order.
    @Query(value = "SELECT o1.product_id, sum(o1.quantity) as count\n" +
            "FROM orders as o1\n" +
            "WHERE EXISTS\n" +
            "(SELECT 1\n" +
            "FROM orders as o2\n" +
            "WHERE o1.user_id = o2.user_id\n" +
            "AND o1.product_id = o2.product_id\n" +
            "AND o1.order_date > o2.order_date)\n" +
            "GROUP BY o1.product_id\n" +
            "order by count desc\n" +
            "LIMIT 3;", nativeQuery = true)
    List<TopThreeReorderedProductsDTO> getTopThreeReordered();
}
