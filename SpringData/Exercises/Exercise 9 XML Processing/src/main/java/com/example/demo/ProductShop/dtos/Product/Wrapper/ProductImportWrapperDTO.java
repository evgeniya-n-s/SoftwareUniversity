package com.example.demo.ProductShop.dtos.Product.Wrapper;

import com.example.demo.ProductShop.dtos.Product.ProductImportDTO;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductImportWrapperDTO {
    @XmlElement(name = "product")
    private List<ProductImportDTO> products;

    public ProductImportWrapperDTO() {
    }

    public List<ProductImportDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductImportDTO> products) {
        this.products = products;
    }
}
