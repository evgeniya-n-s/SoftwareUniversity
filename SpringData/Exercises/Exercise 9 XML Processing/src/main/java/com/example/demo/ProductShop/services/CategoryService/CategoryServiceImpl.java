package com.example.demo.ProductShop.services.CategoryService;

import com.example.demo.ProductShop.dtos.Category.CategoryProductsSummaryDTO;
import com.example.demo.ProductShop.dtos.Category.Wrapper.CategoriesProductSummaryWrapperDTO;
import com.example.demo.ProductShop.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static com.example.demo.ProductShop.constants.Paths.*;
import static com.example.demo.ProductShop.constants.Utils.writeXmlIntoFile;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryProductsSummaryDTO> getCategorySummary() throws IOException, JAXBException {
        List<CategoryProductsSummaryDTO> categoryProductsSummaryDTOS = this.categoryRepository.getCategorySummary()
                .orElseThrow(NoSuchElementException::new);

        CategoriesProductSummaryWrapperDTO categoriesProductSummaryWrapperDTO =
                new CategoriesProductSummaryWrapperDTO(categoryProductsSummaryDTOS);

        writeXmlIntoFile(categoriesProductSummaryWrapperDTO, CATEGORIES_BY_PRODUCTS_XML_PATH);

        return categoryProductsSummaryDTOS;
    }
}
