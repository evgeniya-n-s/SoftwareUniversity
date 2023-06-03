package com.example.ShoppingList.services;

import com.example.ShoppingList.model.enums.CategoryNames;
import com.example.ShoppingList.model.view.ProductViewModel;
import com.example.ShoppingList.model.entity.Product;
import com.example.ShoppingList.model.service.ProductServiceModel;
import com.example.ShoppingList.repository.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, CategoryService categoryService, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    public void addProduct(ProductServiceModel productServiceModel) {
        Product product = modelMapper.map(productServiceModel, Product.class);
        product.setCategory(categoryService.findByNames(productServiceModel.getCategory()));
        productRepository.save(product);
    }

    public BigDecimal totalSum(){
        return productRepository.findTotalProductsSum();
    }


    public List<ProductViewModel> findAllProductsByCategoryName(CategoryNames categoryNames){
        return productRepository.findAllByCategory_Names(categoryNames).stream().map(product -> modelMapper.map(product, ProductViewModel.class)).collect(Collectors.toList());
    }

    public void buyById(long id) {
        productRepository.deleteById(id);
    }

    public void buyAll() {
        productRepository.deleteAll();
    }
}
