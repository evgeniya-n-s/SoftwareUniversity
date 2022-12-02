package com.example.gamestore.repositories;

import com.example.gamestore.entites.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game,Long> {
   // Game findGameById(int id);

    Set <Game> getAllByIdIsNotNull();

    Game findByTitle(String title);

}
