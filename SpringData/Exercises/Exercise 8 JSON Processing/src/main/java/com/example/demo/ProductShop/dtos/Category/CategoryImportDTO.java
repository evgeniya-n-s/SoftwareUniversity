package com.example.demo.ProductShop.dtos.Category;

public class CategoryImportDTO {
    private String name;

    public CategoryImportDTO() {
    }

    public CategoryImportDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
