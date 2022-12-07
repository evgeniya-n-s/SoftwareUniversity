package com.example.demo.ProductShop.entities;

import com.example.demo.ProductShop.messages.ValidationMessages;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product extends Base {
    //id, name (at least 3 characters), price, buyerId (optional) and sellerId as IDs of users.
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User buyer;

    @ManyToOne
    @Fetch(FetchMode.JOIN)
    private User seller;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<Category> categories;

    public Product() {
    }

    public Product(String name, BigDecimal price, User buyer, User seller, Set<Category> categories) {
        this.name = name;
        this.price = price;
        this.buyer = buyer;
        this.seller = seller;
        this.categories = categories;
        validation();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

    public void validation() {
        if (this.name.length() < 3) {
            throw new IllegalArgumentException(ValidationMessages.NAME_SHOULD_BE_AT_LEAST_3_SYMBOLS);
        }
    }
}
