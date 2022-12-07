package com.example.demo.ProductShop.dtos.User;

import com.example.demo.ProductShop.dtos.Product.ProductSoldWithCountDTO;

public class UserWithProductDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private ProductSoldWithCountDTO soldProducts;

    public UserWithProductDTO() {
    }

    public UserWithProductDTO(String firstName, String lastName, Integer age, ProductSoldWithCountDTO soldProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.soldProducts = soldProducts;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public ProductSoldWithCountDTO getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(ProductSoldWithCountDTO soldProducts) {
        this.soldProducts = soldProducts;
    }
}
