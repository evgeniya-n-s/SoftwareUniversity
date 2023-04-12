package com.example.Coffee.service;

import com.example.Coffee.model.entity.Category;
import com.example.Coffee.model.enums.CategoryEnums;
import com.example.Coffee.repository.CategoryRepository;
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
            Arrays.stream(CategoryEnums.values()).forEach(categoryEnums->{
                Category category = new Category().setName(categoryEnums);
                switch (categoryEnums){
                    case Cake -> category.setNeededTime(10);
                    case Drink -> category.setNeededTime(1);
                    case Coffee -> category.setNeededTime(2);
                    case Other -> category.setNeededTime(5);
                }
                categoryRepository.save(category);
            });
        }
    }

    public Category findByCategoryEnums(CategoryEnums category) {
        return categoryRepository.findByName(category).orElse(null);
    }
}
