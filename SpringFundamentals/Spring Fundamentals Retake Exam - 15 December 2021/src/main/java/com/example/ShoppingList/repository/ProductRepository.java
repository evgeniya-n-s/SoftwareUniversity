package com.example.ShoppingList.repository;

import com.example.ShoppingList.model.entity.Product;
import com.example.ShoppingList.model.enums.CategoryNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT SUM(p.price) from Product p")
    BigDecimal findTotalProductsSum();

    List<Product> findAllByCategory_Names(CategoryNames categoryNames);
}
