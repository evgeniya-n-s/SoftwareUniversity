package com.example.demo.ProductShop.services.UserService;

import com.example.demo.ProductShop.dtos.User.UserDTO;
import com.example.demo.ProductShop.dtos.User.UserWithProductDTO;
import com.example.demo.ProductShop.dtos.User.UsersWithProductsWrapperDTO;
import com.example.demo.ProductShop.dtos.User.UsersWithSoldProductDTO;
import com.example.demo.ProductShop.dtos.User.Wrapper.UserWithSoldProductWrapperDTO;
import com.example.demo.ProductShop.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.example.demo.ProductShop.constants.Paths.*;
import static com.example.demo.ProductShop.constants.Utils.*;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UsersWithSoldProductDTO> findAllBySellingProductsBuyerIsNotNull() throws IOException, JAXBException {
        List<UsersWithSoldProductDTO> users = this.userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastNameAscSellingProductsBuyerFirstName()
                .orElseThrow(NoSuchElementException::new)
                .stream().map(user -> MODEL_MAPPER.map(user, UsersWithSoldProductDTO.class))
                .collect(Collectors.toList());

        UserWithSoldProductWrapperDTO userWithSoldProductWrapperDTO =
                new UserWithSoldProductWrapperDTO().ofListOfUsersWithSoldProductsDto(users);

        writeXmlIntoFile(userWithSoldProductWrapperDTO,USERS_SOLD_PRODUCTS_XML_PATH);

        return users;
    }

    @Override
    public UsersWithProductsWrapperDTO usersAndProducts() throws IOException, JAXBException {
        List<UserWithProductDTO> userAndProduct = this.userRepository.findAllBySellingProductsBuyerIsNotNullOrderBySellingProductsBuyerLastNameAscSellingProductsBuyerFirstName()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> MODEL_MAPPER.map(user, UserDTO.class))
                .map(UserDTO::userWithProductDTO)
                .collect(Collectors.toList());

        UsersWithProductsWrapperDTO usersWithProductsWrapperDTO = new UsersWithProductsWrapperDTO(userAndProduct);

        writeXmlIntoFile(usersWithProductsWrapperDTO, USERS_AND_PRODUCTS_XML_PATH);

        return usersWithProductsWrapperDTO;
    }

}
