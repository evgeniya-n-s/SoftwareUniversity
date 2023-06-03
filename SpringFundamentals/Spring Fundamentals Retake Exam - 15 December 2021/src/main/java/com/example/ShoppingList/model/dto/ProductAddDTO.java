package com.example.ShoppingList.model.dto;

import com.example.ShoppingList.model.enums.CategoryNames;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ProductAddDTO {

    @Size(min = 3,max = 20, message = "Name length must be between 3 and 20 characters")
    @NotBlank(message = "Name cannot be empty")
    private String name;

    @Size(min = 5, message = "Description length must be min 5 characters")
    private String description;

    @FutureOrPresent(message = "The date cannot be in the past")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime before;

    @Positive(message = "Price must be positive")
    @NotNull(message = "Price cannot be empty")
    private BigDecimal price;

    @NotNull(message = "You must select category")
    private CategoryNames category;

    public ProductAddDTO() {
    }

    public String getName() {
        return name;
    }

    public ProductAddDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ProductAddDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public LocalDateTime getBefore() {
        return before;
    }

    public ProductAddDTO setBefore(LocalDateTime before) {
        this.before = before;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductAddDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public CategoryNames getCategory() {
        return category;
    }

    public ProductAddDTO setCategory(CategoryNames category) {
        this.category = category;
        return this;
    }
}
