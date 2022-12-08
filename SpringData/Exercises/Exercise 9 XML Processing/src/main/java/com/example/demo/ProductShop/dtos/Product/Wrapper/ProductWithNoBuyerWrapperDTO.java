package com.example.demo.ProductShop.dtos.Product.Wrapper;

import com.example.demo.ProductShop.dtos.Product.ProductWithNoBuyerDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWithNoBuyerWrapperDTO {
    @XmlElement(name = "product")
    private List<ProductWithNoBuyerDTO> products;

    public ProductWithNoBuyerWrapperDTO() {
    }

    public ProductWithNoBuyerWrapperDTO(List<ProductWithNoBuyerDTO> products) {
        this.products = products;
    }

    public List<ProductWithNoBuyerDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductWithNoBuyerDTO> products) {
        this.products = products;
    }
}
