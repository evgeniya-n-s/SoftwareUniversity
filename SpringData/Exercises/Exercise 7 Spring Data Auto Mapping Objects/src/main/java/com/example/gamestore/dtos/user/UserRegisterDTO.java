package com.example.gamestore.dtos.user;

import com.example.gamestore.constants.Validation;
import com.example.gamestore.messages.ValidationMessages;

import java.util.regex.Pattern;

public class UserRegisterDTO {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDTO(String email, String password, String confirmPassword, String fullName) {
        setEmail(email);
        setPassword(password);
        setConfirmPassword(confirmPassword);
        setFullName(fullName);
        validation();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    private void validation() {
        boolean isCorrectEmail = Pattern.matches(Validation.EMAIL_PATTERN, this.email);
        if (!isCorrectEmail) {
            throw new IllegalArgumentException(ValidationMessages.EMAIL_VALIDATION_MESSAGE);
        }

        boolean isCorrectPassword = Pattern.matches(Validation.PASSWORD_PATTERN, this.password);
        if (!isCorrectPassword) {
            throw new IllegalArgumentException(ValidationMessages.PASSWORD_VALIDATION_MESSAGE);
        }
        if (!this.password.equals(confirmPassword)) {
            throw new IllegalArgumentException(ValidationMessages.PASSWORD_MISS_MATCH_MESSAGE);
        }
    }

    @Override
    public String toString() {
        return fullName + " was registered";
    }
}
