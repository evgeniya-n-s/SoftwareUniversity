package com.example.Coffee.model.service;

import com.example.Coffee.model.entity.Category;
import com.example.Coffee.model.entity.User;
import com.example.Coffee.model.enums.CategoryEnums;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderServiceModel {

    private Long id;
    private String name;

    private String description;

    private BigDecimal price;

    private LocalDateTime orderTime;

    private CategoryEnums category;

    private User user;

    public OrderServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public OrderServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OrderServiceModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OrderServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OrderServiceModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public LocalDateTime getOrderTime() {
        return orderTime;
    }

    public OrderServiceModel setOrderTime(LocalDateTime orderTime) {
        this.orderTime = orderTime;
        return this;
    }



    public User getUser() {
        return user;
    }

    public OrderServiceModel setUser(User user) {
        this.user = user;
        return this;
    }

    public CategoryEnums getCategory() {
        return category;
    }

    public OrderServiceModel setCategory(CategoryEnums category) {
        this.category = category;
        return this;
    }
}
