package com.example.Coffee.init;

import com.example.Coffee.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DateBaseInit implements CommandLineRunner {

    private final CategoryService categoryService;

    public DateBaseInit(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
       categoryService.initCategories();
    }
}
