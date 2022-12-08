package com.example.demo.ProductShop.services.CategoryService;

import com.example.demo.ProductShop.dtos.Category.CategoryProductsSummaryDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CategoryService {
    List<CategoryProductsSummaryDTO> getCategorySummary() throws IOException, JAXBException;
}
