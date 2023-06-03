package com.example.ShoppingList.model.service;

import com.example.ShoppingList.model.enums.CategoryNames;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductServiceModel {
    private long id;
    private String name;
    private String description;
    private LocalDateTime before;
    private BigDecimal price;
    private CategoryNames category;

    public ProductServiceModel() {
    }

    public long getId() {
        return id;
    }

    public ProductServiceModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getBefore() {
        return before;
    }

    public ProductServiceModel setBefore(LocalDateTime before) {
        this.before = before;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryNames getCategory() {
        return category;
    }

    public ProductServiceModel setCategory(CategoryNames category) {
        this.category = category;
        return this;
    }
}
