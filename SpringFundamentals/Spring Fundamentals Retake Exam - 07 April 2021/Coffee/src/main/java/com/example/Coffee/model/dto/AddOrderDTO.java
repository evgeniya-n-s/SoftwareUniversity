package com.example.Coffee.model.dto;

import com.example.Coffee.model.enums.CategoryEnums;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AddOrderDTO {
    @Size(min = 3, max = 20)
    private String name;
    @Size(min = 5)
    private String description;

    @Positive
    @NotNull
    private BigDecimal price;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime orderTime;

   @NotNull
   private CategoryEnums category;

    public AddOrderDTO() {
    }

    public String getName() {
        return name;
    }

    public AddOrderDTO setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AddOrderDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AddOrderDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public AddOrderDTO setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }

    public CategoryEnums getCategory() {
        return category;
    }

    public AddOrderDTO setCategory(CategoryEnums category) {
        this.category = category;
        return this;
    }
}
