package com.example.demo.ProductShop.repositories;

import com.example.demo.ProductShop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "Select * from `product_shop`.users order by Rand() LIMIT 1", nativeQuery = true)
    Optional<User> findRandomEntity();

    Optional<List<User>> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastNameAscSellingProductsBuyerFirstName();

    Optional<List<User>> findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerFirstName();
}
