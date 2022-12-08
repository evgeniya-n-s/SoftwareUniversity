package com.example.demo.ProductShop.dtos.Category.Wrapper;

import com.example.demo.ProductShop.dtos.Category.CategoryProductsSummaryDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoriesProductSummaryWrapperDTO {

    @XmlElement(name = "category")
    private List<CategoryProductsSummaryDTO> categories;

    public CategoriesProductSummaryWrapperDTO() {
    }

    public CategoriesProductSummaryWrapperDTO(List<CategoryProductsSummaryDTO> categories) {
        this.categories = categories;
    }

    public List<CategoryProductsSummaryDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryProductsSummaryDTO> categories) {
        this.categories = categories;
    }
}
