package com.example.demo.ProductShop.entities;

import com.example.demo.ProductShop.messages.ValidationMessages;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")

public class User extends Base {
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name", nullable = false)
    private String lastName;
    private Integer age;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller")
    @Fetch(FetchMode.JOIN)
    private Set<Product> sellingProducts;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer")
    @Fetch(FetchMode.JOIN)
    private Set<Product> boughtProducts;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<User> friends;

    public User() {
    }

    public User(String firstName, String lastName, Integer age, Set<Product> sellingProducts, Set<Product> boughtProducts, Set<User> friends) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sellingProducts = sellingProducts;
        this.boughtProducts = boughtProducts;
        this.friends = friends;
        validation();
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

    public Set<Product> getSellingProducts() {
        return sellingProducts;
    }

    public void setSellingProducts(Set<Product> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    public Set<Product> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(Set<Product> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<User> getFriends() {
        return friends;
    }

    public void setFriends(Set<User> friends) {
        this.friends = friends;
    }

    public void validation() {
        if (this.lastName.length() < 3) {
            throw new IllegalArgumentException(ValidationMessages.LAST_NAME_SHOULD_BE_AT_LEAST_3_SYMBOLS);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, getId());
    }
}
