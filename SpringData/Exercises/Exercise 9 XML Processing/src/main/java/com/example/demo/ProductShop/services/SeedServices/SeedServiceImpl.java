package com.example.demo.ProductShop.services.SeedServices;

import com.example.demo.ProductShop.dtos.Category.Wrapper.CategoryImportWrapperDTO;
import com.example.demo.ProductShop.dtos.Product.Wrapper.ProductImportWrapperDTO;
import com.example.demo.ProductShop.dtos.User.Wrapper.UsersImportWrapperDto;
import com.example.demo.ProductShop.entities.Category;
import com.example.demo.ProductShop.entities.Product;
import com.example.demo.ProductShop.entities.User;
import com.example.demo.ProductShop.repositories.CategoryRepository;
import com.example.demo.ProductShop.repositories.ProductRepository;
import com.example.demo.ProductShop.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.example.demo.ProductShop.constants.Paths.*;
import static com.example.demo.ProductShop.constants.Utils.*;

@Service
public class  SeedServiceImpl implements SeedService {

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
    public void seedUsers() throws IOException, JAXBException {
        if (this.userRepository.count() == 0) {
            FileReader fileReader = new FileReader(USER_XML_PATH.toFile());
            JAXBContext context = JAXBContext.newInstance(UsersImportWrapperDto.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            UsersImportWrapperDto usersDTO = (UsersImportWrapperDto) unmarshaller.unmarshal(fileReader);
            List<User> users = usersDTO.getUsers().stream()
                    .map(userImportDTO -> MODEL_MAPPER.map(userImportDTO, User.class)).collect(Collectors.toList());
            this.userRepository.saveAllAndFlush(users);
            fileReader.close();
        }
    }

    @Override
    public void seedCategories() throws IOException, JAXBException {
        if (this.categoryRepository.count() == 0) {
            FileReader fileReader = new FileReader(CATEGORY_XML_PATH.toFile());
            JAXBContext context = JAXBContext.newInstance(CategoryImportWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            CategoryImportWrapperDTO categoryDTO = (CategoryImportWrapperDTO) unmarshaller.unmarshal(fileReader);
            List<Category> categories = categoryDTO.getCategories().stream()
                    .map(categoryImportDTO -> MODEL_MAPPER.map(categoryImportDTO, Category.class)).collect(Collectors.toList());
            this.categoryRepository.saveAllAndFlush(categories);
            fileReader.close();

        }
    }

    @Override
    public void seedProducts() throws IOException, JAXBException {
        if (this.productRepository.count() == 0) {
            FileReader fileReader = new FileReader(PRODUCT_XML_PATH.toFile());

            JAXBContext context = JAXBContext.newInstance(ProductImportWrapperDTO.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            ProductImportWrapperDTO productDTO = (ProductImportWrapperDTO) unmarshaller.unmarshal(fileReader);
            List<Product> products = productDTO.getProducts().stream()
                    .map(productImportDTO -> MODEL_MAPPER.map(productImportDTO, Product.class))
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
