package com.example.ShoppingList.model.view;

import java.math.BigDecimal;

public class ProductViewModel {

    private long id;
    private String name;
    private BigDecimal price;

    public ProductViewModel() {
    }

    public long getId() {
        return id;
    }

    public ProductViewModel setId(long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
