package com.example.gamestore.services.users;

import com.example.gamestore.dtos.user.UserLoginDTO;
import com.example.gamestore.dtos.user.UserRegisterDTO;
import com.example.gamestore.entites.User;
import com.example.gamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.gamestore.messages.ValidationMessages.*;


@Service
public class UserServiceImpl implements UserService {
    private User loggedUser;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(String[] arg) {
        String email = arg[1];
        String password = arg[2];
        String confirmPassword = arg[3];
        String fuuName = arg[4];

        UserRegisterDTO userRegister;
        try {
            userRegister = new UserRegisterDTO(email, password, confirmPassword, fuuName);
        } catch (IllegalArgumentException exception) {
            return exception.getMessage();
        }

        User user = this.modelMapper.map(userRegister, User.class);

        if (this.userRepository.count() == 0) {
            user.setAdministrator(true);
        }

        boolean isUserFound = this.userRepository.findFirstByEmail(userRegister.getEmail()).isPresent();

        if (isUserFound) {
            return EMAIL_ALREADY_EXIST;
        }

        this.userRepository.save(user);

        return userRegister.toString();
    }

    @Override
    public String loginUser(String[] arg) {
        String email = arg[1];
        String password = arg[2];

        UserLoginDTO userLogin = new UserLoginDTO(email, password);

        Optional<User> existEmail = this.userRepository.findFirstByEmail(userLogin.getEmail());

        if (existEmail.isPresent() && this.loggedUser == null && existEmail.get().getPassword().equals(userLogin.getPassword())) {
            this.loggedUser = this.userRepository.findFirstByEmail(userLogin.getEmail()).get();
            return SUCCESSFUL_LOGIN + this.loggedUser.getFullName();
        }

        return PASSWORD_VALIDATION_MESSAGE;
    }

    @Override
    public String logoutUser() {
        if (this.loggedUser == null) {
            return LOGOUT_NON_USER;
        }
        String output = String.format(SUCCESSFUL_LOGOUT, loggedUser.getFullName());
        this.loggedUser = null;
        return output;
    }

    @Override
    public User getLoggedInSystem() {
        return this.loggedUser;
    }
}
