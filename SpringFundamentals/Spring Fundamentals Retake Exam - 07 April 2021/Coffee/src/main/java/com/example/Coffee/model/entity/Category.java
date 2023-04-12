package com.example.Coffee.model.entity;

import com.example.Coffee.model.enums.CategoryEnums;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity{

    @Enumerated(value = EnumType.STRING)
    private CategoryEnums name;

    @Column(nullable = false)
    private Integer neededTime;

    public Category() {
    }

    public CategoryEnums getName() {
        return name;
    }

    public Category setName(CategoryEnums name) {
        this.name = name;
        return this;
    }

    public Integer getNeededTime() {
        return neededTime;
    }

    public Category setNeededTime(Integer neededTime) {
        this.neededTime = neededTime;
        return this;
    }
}
