package com.example.demo.ProductShop.entities;

import com.example.demo.ProductShop.messages.ValidationMessages;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "categories")
public class Category extends Base {
    //have an id and name (from 3 to 15 characters)
    @Column(nullable = false)
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
        validation();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void validation() {
        if (this.name.length() < 3 || this.name.length() > 15) {
            throw new IllegalArgumentException(ValidationMessages.NAME_SHOULD_BE_BETWEEN_3_AND_15_SYMBOLS);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name)
                && Objects.equals(getId(), category.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, getId());
    }
}
