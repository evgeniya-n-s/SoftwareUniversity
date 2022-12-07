package com.example.demo.ProductShop.services.CategoryService;

import com.example.demo.ProductShop.dtos.Category.CategoryProductsSummaryDTO;
import com.example.demo.ProductShop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.demo.ProductShop.constants.Paths.*;
import static com.example.demo.ProductShop.constants.Utils.writeJSONtoFile;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryProductsSummaryDTO> getCategorySummary() throws IOException {
        List<CategoryProductsSummaryDTO> categoryProductsSummaryDTOS = this.categoryRepository.getCategorySummary()
                .orElseThrow(NoSuchElementException::new);

        writeJSONtoFile(categoryProductsSummaryDTOS, CATEGORIES_BY_PRODUCTS_JSON_PATH);

        return categoryProductsSummaryDTOS;
    }
}
