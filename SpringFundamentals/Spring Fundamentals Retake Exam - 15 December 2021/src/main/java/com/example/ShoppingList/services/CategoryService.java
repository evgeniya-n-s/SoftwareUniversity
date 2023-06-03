package com.example.ShoppingList.services;

import com.example.ShoppingList.model.entity.Category;
import com.example.ShoppingList.model.enums.CategoryNames;
import com.example.ShoppingList.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public void initCategories() {
        if(categoryRepository.count() == 0){
            Arrays.stream(CategoryNames.values()).forEach(categoryNames -> {
                Category category = new Category(categoryNames,
                        "Description for " + categoryNames.name());
                categoryRepository.save(category);
            });
        }
    }

    public Category findByNames (CategoryNames categoryNames){
        return categoryRepository.findByNames(categoryNames).orElse(null);
    }
}
