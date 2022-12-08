package com.example.demo.ProductShop.dtos.User;

import com.example.demo.ProductShop.dtos.Product.ProductSoldDTO;
import com.example.demo.ProductShop.dtos.Product.Wrapper.ProductsSoldWrapperDTO;

import java.util.List;
import java.util.stream.Collectors;

public class UsersWithSoldProductDTO {
    private String firstName;

    private String lastName;
    private List<ProductSoldDTO> boughtProducts;

    public UsersWithSoldProductDTO() {
    }

    public UsersWithSoldProductDTO(String firstName, String lastName, List<ProductSoldDTO> boughtProducts) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.boughtProducts = boughtProducts;
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

    public List<ProductSoldDTO> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<ProductSoldDTO> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }


    public static List<UserWithSoldProductXMLDTO> toUsersWithSoldProductsDto(List<UsersWithSoldProductDTO> input) {
        return input.stream()
                .map(user -> new UserWithSoldProductXMLDTO(
                        user.getFirstName(),
                        user.getLastName(),
                        new ProductsSoldWrapperDTO(user.getBoughtProducts())))
                .collect(Collectors.toList());
    }
}
