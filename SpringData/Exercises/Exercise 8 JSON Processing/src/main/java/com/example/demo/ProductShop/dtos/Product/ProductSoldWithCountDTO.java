package com.example.demo.ProductShop.dtos.Product;

import java.util.List;

public class ProductSoldWithCountDTO {
    private Integer count;
    private List<ProductBasicInfoDTO> products;

    public ProductSoldWithCountDTO() {
    }

    public ProductSoldWithCountDTO(List<ProductBasicInfoDTO> products) {
        this.products = products;
        this.count = products.size();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<ProductBasicInfoDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ProductBasicInfoDTO> products) {
        this.products = products;
    }
}
