package com.example.demo.ProductShop.services.ProductService;

import com.example.demo.ProductShop.dtos.Product.ProductWithNoBuyerDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    List<ProductWithNoBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high) throws IOException, JAXBException;
}
