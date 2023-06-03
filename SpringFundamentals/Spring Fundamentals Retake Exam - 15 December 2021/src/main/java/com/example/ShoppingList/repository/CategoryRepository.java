package com.example.ShoppingList.repository;

import com.example.ShoppingList.model.entity.Category;
import com.example.ShoppingList.model.enums.CategoryNames;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByNames(CategoryNames names);
}
