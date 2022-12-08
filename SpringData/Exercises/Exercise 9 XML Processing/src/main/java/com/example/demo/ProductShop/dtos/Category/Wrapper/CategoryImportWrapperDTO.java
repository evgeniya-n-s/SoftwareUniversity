package com.example.demo.ProductShop.dtos.Category.Wrapper;

import com.example.demo.ProductShop.dtos.Category.CategoryImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(XmlAccessType.FIELD)
public class CategoryImportWrapperDTO {

    @XmlElement(name = "category")
    private List<CategoryImportDTO> categories;

    public CategoryImportWrapperDTO() {
    }

    public List<CategoryImportDTO> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoryImportDTO> categories) {
        this.categories = categories;
    }
}
