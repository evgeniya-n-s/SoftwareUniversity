package com.example.Coffee.model.dto;

import jakarta.validation.constraints.Size;

public class UserLoginDTO {
    @Size(min = 5,max = 20)
    private String username;

    @Size(min = 3)
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
