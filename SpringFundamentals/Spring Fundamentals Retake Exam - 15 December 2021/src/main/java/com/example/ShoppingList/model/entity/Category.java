package com.example.ShoppingList.model.entity;

import com.example.ShoppingList.model.enums.CategoryNames;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false, unique = true)
    private CategoryNames names;

    @Column(columnDefinition = "TEXT")
    private String description;

    @OneToMany(mappedBy = "category", fetch = FetchType.EAGER)
    private Set<Product> products;


    public Category(CategoryNames names, String description) {
        this.names = names;
        this.description = description;
    }

    public Category() {
    }

    public CategoryNames getNames() {
        return names;
    }

    public Category setNames(CategoryNames names) {
        this.names = names;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
