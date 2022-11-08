package com.example.demo.services;

import com.example.demo.models.User;

public interface UserService {
    void register(String username, int age);
    User findByUsername(String username);
}
