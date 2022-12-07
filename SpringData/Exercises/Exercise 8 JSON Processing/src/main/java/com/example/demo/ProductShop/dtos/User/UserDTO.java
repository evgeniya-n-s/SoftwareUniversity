package com.example.demo.ProductShop.dtos.User;

import com.example.demo.ProductShop.dtos.Product.ProductDTO;
import com.example.demo.ProductShop.dtos.Product.ProductSoldWithCountDTO;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDTO {
    private String firstName;
    private String lastName;
    private Integer age;
    private List<ProductDTO> sellingProducts;
    private List<ProductDTO> boughtProducts;
    private Set<UserDTO> friends;

    public UserDTO() {
    }

    public UserDTO(String firstName, String lastName, Integer age, List<ProductDTO> sellingProducts, List<ProductDTO> boughtProducts, Set<UserDTO> friends) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sellingProducts = sellingProducts;
        this.boughtProducts = boughtProducts;
        this.friends = friends;
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

    public List<ProductDTO> getSellingProducts() {
        return sellingProducts;
    }

    public void setSellingProducts(List<ProductDTO> sellingProducts) {
        this.sellingProducts = sellingProducts;
    }

    public List<ProductDTO> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<ProductDTO> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public Set<UserDTO> getFriends() {
        return friends;
    }

    public void setFriends(Set<UserDTO> friends) {
        this.friends = friends;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }


    public UserWithProductDTO userWithProductDTO() {
        return new UserWithProductDTO(firstName, lastName, age, productSoldWithCountDTO(sellingProducts));
    }

    private ProductSoldWithCountDTO productSoldWithCountDTO(List<ProductDTO> sellingProducts) {
        return new ProductSoldWithCountDTO(sellingProducts.stream()
                .map(ProductDTO::productBasicInfoDTO).collect(Collectors.toList()));
    }

}
