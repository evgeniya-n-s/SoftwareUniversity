package com.example.Coffee.service;

import com.example.Coffee.model.entity.User;
import com.example.Coffee.model.service.UserServiceModel;
import com.example.Coffee.model.view.UserViewModel;
import com.example.Coffee.repository.UserRepository;
import com.example.Coffee.security.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    private final CurrentUser currentUser;

    public UserService(ModelMapper modelMapper, UserRepository userRepository, CurrentUser currentUser) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.currentUser = currentUser;
    }

    public void userRegister(UserServiceModel userServiceModel) {

        User user = modelMapper.map(userServiceModel, User.class);
        userRepository.save(user);
    }

    public UserServiceModel findByUserNameAndPassword(String username, String password){

       return userRepository.findByUsernameAndPassword(username,password).
               map(user -> modelMapper.map(user,UserServiceModel.class)).orElse(null);
    }

    public void loginUser(Long id, String username) {
        currentUser.setId(id);
        currentUser.setUsername(username);
    }


    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserViewModel> findAllUsersAndCountOfOrdersDesc() {
        return userRepository.findAllByOrdersCountDesc().stream().map(user -> {
            UserViewModel userViewModel = new UserViewModel();
            userViewModel.setUsername(user.getUsername());
            userViewModel.setCountOfOrders(user.getOrders().size());

            return userViewModel;
        }).collect(Collectors.toList());
    }
}
