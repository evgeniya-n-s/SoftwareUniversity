package com.example.demo.ProductShop.dtos.Product.Wrapper;

import com.example.demo.ProductShop.dtos.Product.ProductSoldDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductsSoldWrapperDTO {
    @XmlElement(name = "product")
    private List<ProductSoldDTO> boughtProducts;

    public ProductsSoldWrapperDTO() {
    }

    public ProductsSoldWrapperDTO(List<ProductSoldDTO> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }

    public List<ProductSoldDTO> getBoughtProducts() {
        return boughtProducts;
    }

    public void setBoughtProducts(List<ProductSoldDTO> boughtProducts) {
        this.boughtProducts = boughtProducts;
    }
}
