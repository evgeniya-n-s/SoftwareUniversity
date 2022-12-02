package com.example.gamestore;

import com.example.gamestore.services.games.GameService;
import com.example.gamestore.services.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

import static com.example.gamestore.commands.Command.*;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
    private static final Scanner scanner = new Scanner(System.in);
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public CommandLineRunnerImpl(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        String input =scanner.nextLine();
        while (!input.equals("close")){
            String arguments[] = input.split("\\|");
            String command = arguments[0];

            String output = switch (command){
                case REGISTER_USER ->userService.registerUser(arguments);
                case LOGIN_USER -> userService.loginUser(arguments);
                case LOGOUT_USER -> userService.logoutUser();
                case ADD_GAME -> gameService.addGame(arguments);
                case EDIT_GAME -> gameService.editGame(arguments);
                case DELETE_GAME -> gameService.deleteGame(arguments);
                case PRINT_ALL_GAMES -> gameService.allGames();
                case PRINT_DETAILS_GAME -> gameService.detailsGame(arguments);
                case PRINT_OWNED_GAMES -> gameService.ownedGames();
                default ->String.format(COMMAND_NOT_FOUND);
            };
            System.out.println(output);
            input = scanner.nextLine();
        }

    }
}
