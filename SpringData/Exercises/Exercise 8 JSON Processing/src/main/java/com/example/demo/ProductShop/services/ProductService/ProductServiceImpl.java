package com.example.demo.ProductShop.services.ProductService;

import com.example.demo.ProductShop.dtos.Product.ProductDTO;
import com.example.demo.ProductShop.dtos.Product.ProductWithNoBuyerDTO;
import com.example.demo.ProductShop.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.demo.ProductShop.constants.Paths.*;
import static com.example.demo.ProductShop.constants.Utils.*;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<ProductWithNoBuyerDTO> findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal low, BigDecimal high) throws IOException {
        List<ProductWithNoBuyerDTO> products = this.productRepository.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(low, high)
                .orElseThrow(NoSuchElementException::new)
                .stream().map(product -> MODEL_MAPPER.map(product, ProductDTO.class))
                .map(ProductDTO::productWithNoBuyerDTO)
                .collect(Collectors.toList());

        writeJSONtoFile(products, PRODUCTS_IN_RANGE_JSON_PATH);
        return products;
    }
}
