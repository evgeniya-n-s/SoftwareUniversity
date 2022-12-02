package com.example.gamestore.messages;

public enum ValidationMessages {
    ;
    public static final String EMAIL_VALIDATION_MESSAGE = "Incorrect email.";
    public static final String PASSWORD_VALIDATION_MESSAGE = "Incorrect username / password";
    public static final String PASSWORD_MISS_MATCH_MESSAGE = "Passwords are not matches";
    public static final String EMAIL_ALREADY_EXIST = "Email already exist.";
    public static final String SUCCESSFUL_LOGIN = "Successfully logged in ";
    public static final String SUCCESSFUL_LOGOUT = "User %s successfully logged out";
    public static final String LOGOUT_NON_USER = "Cannot log out. No user was logged in.";
    public static final String NOT_VALID_GAME = "Not a valid game title";
    public static final String PRICE_MUST_POSITIVE_NUMBER = "Price must be a positive number";
    public static final String SIZE_MUST_POSITIVE_NUMBER = "Size must be a positive number.";
    public static final String TRAILER_ID_MUST_BE_11_SYMBOL = "Trailer ID must be exactly 11 symbol.";
    public static final String THUMBNAIL_URL_MUST_START_WITH = "URL should be starting with http:// or https://.";
    public static final String DESCRIPTION_MUST_20_SYMBOLS = "Description text must be at least 20 symbols.";
    public static final String NOT_ADMINISTRATOR = "Impossible command";
}
