package com.example.Coffee.repository;

import com.example.Coffee.model.entity.Category;
import com.example.Coffee.model.enums.CategoryEnums;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByName (CategoryEnums category);
}
