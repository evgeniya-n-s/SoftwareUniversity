package com.example.demo.ProductShop.repositories;

import com.example.demo.ProductShop.dtos.Category.CategoryProductsSummaryDTO;
import com.example.demo.ProductShop.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    @Query(value = "Select * from `product_shop`.categories order by Rand() LIMIT 1", nativeQuery = true)
    Optional<Category> findRandomEntity();


    @Query("select new com.example.demo.ProductShop.dtos.Category.CategoryProductsSummaryDTO" +
            "(c.name, count(p.id), AVG(p.price), SUM(p.price)) " +
            "from Product p JOIN p.categories c GROUP BY c.id ORDER BY count(p.id)")
    Optional<List<CategoryProductsSummaryDTO>> getCategorySummary();

}
