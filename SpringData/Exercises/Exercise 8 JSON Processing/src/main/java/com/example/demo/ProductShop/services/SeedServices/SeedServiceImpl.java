package com.example.demo.ProductShop.services.SeedServices;

import com.example.demo.ProductShop.dtos.Category.CategoryImportDTO;
import com.example.demo.ProductShop.dtos.Product.ProductImportDTO;
import com.example.demo.ProductShop.dtos.User.UserImportDTO;
import com.example.demo.ProductShop.entities.Category;
import com.example.demo.ProductShop.entities.Product;
import com.example.demo.ProductShop.entities.User;
import com.example.demo.ProductShop.repositories.CategoryRepository;
import com.example.demo.ProductShop.repositories.ProductRepository;
import com.example.demo.ProductShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.demo.ProductShop.constants.Paths.*;
import static com.example.demo.ProductShop.constants.Utils.*;

@Service
public class SeedServiceImpl implements SeedService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public SeedServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void seedUsers() throws IOException {
        if (this.userRepository.count() == 0) {
            FileReader fileReader = new FileReader(USER_JSON_PATH.toFile());
            List<User> users = Arrays.stream(GSON.fromJson(fileReader, UserImportDTO[].class))
                    .map(UserImportDTO -> MODEL_MAPPER.map(UserImportDTO, User.class))
                    .collect(Collectors.toList());
            this.userRepository.saveAllAndFlush(users);
            fileReader.close();
        }
    }

    @Override
    public void seedCategories() throws IOException {
        if (this.categoryRepository.count() == 0) {
            FileReader fileReader = new FileReader(CATEGORY_JSON_PATH.toFile());
            List<Category> categories = Arrays.stream(GSON.fromJson(fileReader, CategoryImportDTO[].class))
                    .map(CategoryImportDTO -> MODEL_MAPPER.map(CategoryImportDTO, Category.class))
                    .collect(Collectors.toList());
            this.categoryRepository.saveAllAndFlush(categories);
            fileReader.close();
        }
    }

    @Override
    public void seedProducts() throws IOException {
        if (this.productRepository.count() == 0) {
            FileReader fileReader = new FileReader(PRODUCT_JSON_PATH.toFile());
            List<Product> products = Arrays.stream(GSON.fromJson(fileReader, ProductImportDTO[].class))
                    .map(ProductImportDTO -> MODEL_MAPPER.map(ProductImportDTO, Product.class))
                    .map(this::setRandomSeller)
                    .map(this::setRandomBuyer)
                    .map(this::setRandomCategories)
                    .collect(Collectors.toList());

            this.productRepository.saveAllAndFlush(products);
            fileReader.close();
        }
    }

    private Product setRandomCategories(Product product) {
        Random random = new Random();
        long categoriesDbCount = this.categoryRepository.count();

        int count = random.nextInt((int) categoriesDbCount);

        Set<Category> categories = new HashSet<>();

        IntStream.range(0, count)
                .forEach(number -> {
                    Category category = this.categoryRepository.findRandomEntity().orElseThrow(NoSuchElementException::new);
                    categories.add(category);
                });

        product.setCategories(categories);
        return product;
    }

    private Product setRandomBuyer(Product product) {
        if (product.getPrice().compareTo(BigDecimal.valueOf(700)) > 0) {
            User buyer = this.userRepository.findRandomEntity().orElseThrow(NoSuchElementException::new);

            while (buyer.equals(product.getSeller())) {
                buyer = this.userRepository.findRandomEntity().orElseThrow(NoSuchElementException::new);
            }

            product.setBuyer(buyer);
        }
        return product;
    }

    private Product setRandomSeller(Product product) {
        User seller = this.userRepository.findRandomEntity().orElseThrow(NoSuchElementException::new);
        product.setSeller(seller);
        return product;
    }
}
