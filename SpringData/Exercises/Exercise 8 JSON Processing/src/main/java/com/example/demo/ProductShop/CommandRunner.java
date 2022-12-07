package com.example.demo.ProductShop;

import com.example.demo.ProductShop.services.CategoryService.CategoryService;
import com.example.demo.ProductShop.services.ProductService.ProductService;
import com.example.demo.ProductShop.services.SeedServices.SeedService;
import com.example.demo.ProductShop.services.UserService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class CommandRunner implements CommandLineRunner {
    private final SeedService seedService;
    private final UserService userService;
    private final ProductService productService;

    private final CategoryService categoryService;

    @Autowired
    public CommandRunner(SeedService seedService, UserService userService, ProductService productService, CategoryService categoryService) {
        this.seedService = seedService;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //seed all files in database product_shop
        this.seedService.seedAll();

//1.	Products Shop
        //Query 1 – Products in Range. After you run the query below you will fill the files/output/product-in-range
//        this.productService.findAllByPriceBetweenAndBuyerIsNullOrderByPrice(BigDecimal.valueOf(500),BigDecimal.valueOf(1000));

        //Query 2 – Successfully Sold Products. After you run the query below you will fill the files/output/users-sold-products
//        this.userService.findAllBySellingProductsBuyerIsNotNull();

        //Query 3 – Categories by Products Count. After you run the query below you will fill the files/output/categories-by-products.json
//        this.categoryService.getCategorySummary();

        //Query 4 – Users and Products. After you run the query below you will fill the files/output/users-and-products.json
//        this.userService.usersAndProducts();
    }
}
