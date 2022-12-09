package com.example.football.service.impl;

import com.example.football.models.dto.Team.TeamImportDTO;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.TeamService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

import static com.example.football.constants.Path.*;

@Service
public class TeamServiceImpl implements TeamService {
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final Validator validator;

    @Autowired
    public TeamServiceImpl(TeamRepository teamRepository, TownRepository townRepository) {
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.gson = new GsonBuilder().create();
        this.modelMapper = new ModelMapper();
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Override
    public boolean areImported() {
        return this.teamRepository.count() > 0;
    }

    @Override
    public String readTeamsFileContent() throws IOException {
        return Files.readString(TEAMS_JSON_PATH);
    }

    @Override
    public String importTeams() throws IOException {
        String json = this.readTeamsFileContent();
        TeamImportDTO[] teamImportDTOS = this.gson.fromJson(json, TeamImportDTO[].class);

        return Arrays.stream(teamImportDTOS)
                .map(this::importTeam)
                .collect(Collectors.joining("\n"));
    }

    private String importTeam(TeamImportDTO dto) {
        Set<ConstraintViolation<TeamImportDTO>> validationErrors = this.validator.validate(dto);
        if (!validationErrors.isEmpty()) {
            return "Invalid Team";
        }

        Optional<Team> teamOptional = this.teamRepository.findByName(dto.getName());
        if (teamOptional.isPresent()) {
            return "Invalid Team";
        }

        Team team = this.modelMapper.map(dto, Team.class);
        Optional<Town> town = this.townRepository.findByName(dto.getTownName());
        team.setTown(town.get());
        this.teamRepository.save(team);
        String msg = String.format("Successfully imported Team %s - %d",
                dto.getName(), dto.getFanBase());
        return msg;
    }
}
