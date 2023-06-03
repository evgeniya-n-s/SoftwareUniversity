package com.example.ShoppingList.services;

import com.example.ShoppingList.model.entity.User;
import com.example.ShoppingList.model.service.UserServiceModel;
import com.example.ShoppingList.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserService(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    public void register(UserServiceModel userServiceModel) {
         userRepository.save(modelMapper.map(userServiceModel, User.class));
    }

    public UserServiceModel findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username,password).map(
                user -> modelMapper.map(user,UserServiceModel.class)
        ).orElse(null);
    }
}
