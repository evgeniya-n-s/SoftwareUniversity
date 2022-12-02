package com.example.gamestore.services.games;

public interface GameService {
    String addGame(String arg[]);

    String editGame(String arg[]);

    String deleteGame(String arg[]);

    String allGames();

    String detailsGame(String arg[]);

    String ownedGames();

}
