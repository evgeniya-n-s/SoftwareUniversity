package com.example.gamestore.commands;

public enum Command {
    ;
    public static final String REGISTER_USER = "RegisterUser";
    public static final String LOGIN_USER = "LoginUser";
    public static final String LOGOUT_USER = "Logout";
    public static final String ADD_GAME = "AddGame";
    public static final String EDIT_GAME = "EditGame";
    public static final String DELETE_GAME = "DeleteGame";
    public static final String PRINT_ALL_GAMES = "AllGames";
    public static final String PRINT_DETAILS_GAME = "DetailGame";
    public static final String PRINT_OWNED_GAMES = "OwnedGames";
    public static final String COMMAND_NOT_FOUND = "Command not found";
}
