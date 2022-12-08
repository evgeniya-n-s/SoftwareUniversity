package com.example.demo.ProductShop.dtos.User;

import com.example.demo.ProductShop.dtos.Product.Wrapper.ProductsSoldWrapperDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWithSoldProductXMLDTO {
    @XmlElement(name = "first-name")
    private String firstName;

    @XmlElement(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-products")
    private ProductsSoldWrapperDTO boughtProducts;

    public UserWithSoldProductXMLDTO() {
    }

    public UserWithSoldProductXMLDTO(String firstName, String lastName, ProductsSoldWrapperDTO boughtProducts) {
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

    public ProductsSoldWrapperDTO getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(ProductsSoldWrapperDTO boughtProducts) {
        this.boughtProducts = boughtProducts;
    }
}
