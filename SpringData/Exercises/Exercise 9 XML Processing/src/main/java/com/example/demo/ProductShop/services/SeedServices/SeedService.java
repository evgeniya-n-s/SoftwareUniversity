package com.example.demo.ProductShop.services.SeedServices;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SeedService {
    void seedUsers() throws IOException, JAXBException;

    void seedCategories() throws IOException, JAXBException;

    void seedProducts() throws IOException, JAXBException;

    default void seedAll() throws IOException, JAXBException {
        seedUsers();
        seedCategories();
        seedProducts();
    }
}
