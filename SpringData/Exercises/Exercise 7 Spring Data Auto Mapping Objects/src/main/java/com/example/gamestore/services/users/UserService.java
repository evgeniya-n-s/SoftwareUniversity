package com.example.gamestore.services.users;

import com.example.gamestore.entites.User;

public interface UserService {
    String registerUser(String arg[]);

    String loginUser(String arg[]);

    String logoutUser();

    User getLoggedInSystem();
}
