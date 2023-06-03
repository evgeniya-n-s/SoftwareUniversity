package com.example.ShoppingList.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @Size(min = 3,max = 20, message = "Username length must be between 3 and 20 characters")
    @NotBlank(message = "Username cannot be empty")
    private String username;

    @Size(min = 3,max = 20, message = "Password length must be between 3 and 20 characters")
    @NotBlank(message = "Password cannot be empty")
    private String password;

    public UserLoginDTO() {
    }

    public String getUsername() {
        return username;
    }

    public UserLoginDTO setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserLoginDTO setPassword(String password) {
        this.password = password;
        return this;
    }
}
