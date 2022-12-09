package com.example.football.repository;

import com.example.football.models.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatRepository extends JpaRepository<Stat,Long> {
    Optional<Stat> findByShootingAndPassingAndEndurance(float shooting, float passing, float endurance);
}
