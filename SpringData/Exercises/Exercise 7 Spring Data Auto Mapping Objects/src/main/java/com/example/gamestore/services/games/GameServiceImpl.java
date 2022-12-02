package com.example.gamestore.services.games;

import com.example.gamestore.dtos.game.GameAddDTO;
import com.example.gamestore.dtos.game.GameAllDTO;
import com.example.gamestore.dtos.game.GameEditDTO;
import com.example.gamestore.entites.Game;
import com.example.gamestore.repositories.GameRepository;
import com.example.gamestore.services.users.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;


import static com.example.gamestore.messages.ValidationMessages.*;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final UserService userService;
    private static ModelMapper modelMapper = new ModelMapper();


    @Autowired
    public GameServiceImpl(GameRepository gameRepository, UserService userService) {
        this.gameRepository = gameRepository;
        this.userService = userService;
    }

    @Override
    public String addGame(String[] arg) {
        if (this.userService.getLoggedInSystem() != null && this.userService.getLoggedInSystem().isAdministrator()) {
            String title = arg[1];
            BigDecimal price = new BigDecimal(arg[2]);
            float size = Float.parseFloat(arg[3]);
            String trailer = arg[4];
            String thumbnailURL = arg[5];
            String description = arg[6];
            LocalDate releaseDate = LocalDate.parse(arg[7], DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            GameAddDTO gameAddDTO;
            try {
                gameAddDTO = new GameAddDTO(title, price, size, trailer, thumbnailURL, description, releaseDate);
            } catch (IllegalArgumentException exception) {
                return exception.getMessage();
            }

            Game game = this.modelMapper.map(gameAddDTO, Game.class);

            this.gameRepository.save(game);

            return gameAddDTO.toString();
        }
        return NOT_ADMINISTRATOR;
    }


    @Override
    public String editGame(String[] arg) {
        if (this.userService.getLoggedInSystem() != null && this.userService.getLoggedInSystem().isAdministrator()) {
            Long id = Long.parseLong(arg[1]);
            Game game = gameRepository.findById(id).orElse(null);
            if (game == null) {
                return "Game does not exist!";
            }

            GameEditDTO gameEditDTO = modelMapper.map(game, GameEditDTO.class);

            for (int i = 2; i < arg.length; i++) {
                String[] tokens = arg[i].split("=");
                String valueName = tokens[0];
                String value = tokens[1];
                switch (valueName) {
                    case "title":
                        return "Cannot edit title!";
                    case "price":
                        gameEditDTO.setPrice(new BigDecimal(value));
                        break;
                    case "size":
                        gameEditDTO.setSize(Float.parseFloat(value));
                        break;
                    case "trailer":
                        gameEditDTO.setTrailer(value);
                        break;
                    case "thumbnailUrl":
                        gameEditDTO.setThumbnailURL(value);
                        break;
                    case "description":
                        gameEditDTO.setDescription(value);
                        break;
                }
            }
            Game updateGame = this.modelMapper.map(gameEditDTO, Game.class);
            updateGame.setId(id);
            this.gameRepository.saveAndFlush(updateGame);

            return gameEditDTO.toString();
        }
        return NOT_ADMINISTRATOR;
    }


    @Override
    public String deleteGame(String arg[]) {
        if (this.userService.getLoggedInSystem() != null && this.userService.getLoggedInSystem().isAdministrator()) {
            Long id = Long.parseLong(arg[1]);
            Game game = gameRepository.findById(id).orElse(null);
            if (game == null) {
                return "Game does not exist!";
            }

            this.gameRepository.delete(game);
            return "Deleted " + game.getTitle();
        }
        return NOT_ADMINISTRATOR;
    }

    @Override
    public String allGames() {
        Set<Game> games = this.gameRepository.getAllByIdIsNotNull();
        return games.stream().map(g -> {
            GameAllDTO gameAllDTO = modelMapper.map(g, GameAllDTO.class);
            return String.format("%s %.2f\n", g.getTitle(), g.getPrice());
        }).collect(Collectors.joining(""));
    }

    @Override
    public String detailsGame(String[] arg) {
        String title = arg[1];
        Game game = this.gameRepository.findByTitle(title);
        if (game == null) {
            return "There isn't a game with title " + title;
        }

        return String.format("Title: %s%nPrice: %.2f%nDescription: %s%nRelease date: %s", game.getTitle(), game.getPrice(), game.getDescription(), game.getReleaseDate());
    }

    @Override
    public String ownedGames() {
        Set<Game> games = this.gameRepository.getAllByIdIsNotNull();
        if (games == null) {
            return "There isn't titles in GameStore";
        }
        return games.stream().map(g -> {
            GameAllDTO gameAllDTO = modelMapper.map(g, GameAllDTO.class);
            return String.format("%s\n", g.getTitle());
        }).collect(Collectors.joining(""));
    }
}
