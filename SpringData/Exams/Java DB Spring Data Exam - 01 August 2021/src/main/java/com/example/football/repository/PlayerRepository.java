package com.example.football.repository;

import com.example.football.models.entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<Player> findByEmail(String email);

    List<Player> findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(LocalDate after, LocalDate before);
}
