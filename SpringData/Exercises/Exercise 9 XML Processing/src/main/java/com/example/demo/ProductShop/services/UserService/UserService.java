package com.example.demo.ProductShop.services.UserService;

import com.example.demo.ProductShop.dtos.User.UsersWithProductsWrapperDTO;
import com.example.demo.ProductShop.dtos.User.UsersWithSoldProductDTO;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService {
    List<UsersWithSoldProductDTO> findAllBySellingProductsBuyerIsNotNull() throws IOException, JAXBException;

    UsersWithProductsWrapperDTO usersAndProducts() throws IOException, JAXBException;

}
