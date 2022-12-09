package com.example.football.service.impl;

import com.example.football.models.dto.Player.PlayerImportDTO;
import com.example.football.models.dto.Player.PlayerImportWrapperDTO;
import com.example.football.models.entity.Player;
import com.example.football.models.entity.Stat;
import com.example.football.models.entity.Team;
import com.example.football.models.entity.Town;
import com.example.football.repository.PlayerRepository;
import com.example.football.repository.StatRepository;
import com.example.football.repository.TeamRepository;
import com.example.football.repository.TownRepository;
import com.example.football.service.PlayerService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.football.constants.Path.*;

@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;
    private final TownRepository townRepository;
    private final StatRepository statRepository;

    private final Unmarshaller unmarshaller;
    private final Validator validator;
    private final ModelMapper modelMapper;

    @Autowired
    public PlayerServiceImpl(PlayerRepository playerRepository, TeamRepository teamRepository, TownRepository townRepository, StatRepository statRepository) throws JAXBException {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
        this.townRepository = townRepository;
        this.statRepository = statRepository;

        JAXBContext context = JAXBContext.newInstance(PlayerImportWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();

        this.validator = Validation
                .buildDefaultValidatorFactory()
                .getValidator();

        this.modelMapper = new ModelMapper();

        this.modelMapper.addConverter(ctx -> LocalDate.parse(ctx.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.class, LocalDate.class);

    }

    @Override
    public boolean areImported() {
        return this.playerRepository.count() >0;
    }

    @Override
    public String readPlayersFileContent() throws IOException {
        return Files.readString(PLAYERS_XML_PATH);
    }

    @Override
    public String importPlayers() throws FileNotFoundException, JAXBException {
        PlayerImportWrapperDTO playerDTOs = (PlayerImportWrapperDTO) this.unmarshaller.unmarshal(
                new FileReader(PLAYERS_XML_PATH.toAbsolutePath().toString()));

        return playerDTOs
                .getPlayers()
                .stream()
                .map(this::importPlayer)
                .collect(Collectors.joining("\n"));
    }

    private String importPlayer(PlayerImportDTO dto) {
        Set<ConstraintViolation<PlayerImportDTO>> errors =
                this.validator.validate(dto);

        if (!errors.isEmpty()) {
            return "Invalid Player";
        }

        Optional<Player> optPlayer = this.playerRepository.findByEmail(dto.getEmail());

        if (optPlayer.isPresent()) {
            return "Invalid Player";
        }

        Optional<Team> team = this.teamRepository.findByName(dto.getTeam().getName());
        Optional<Town> town = this.townRepository.findByName(dto.getTown().getName());
        Optional<Stat> stat = this.statRepository.findById(dto.getStat().getId());

        Player player = this.modelMapper.map(dto, Player.class);

        player.setTeam(team.get());
        player.setTown(town.get());
        player.setStat(stat.get());

        this.playerRepository.save(player);

        return "Successfully imported Player " + player.getFirstName() + " " +
                player.getLastName() + " - " + player.getPosition().toString();

    }

    @Override
    public String exportBestPlayers() {
        LocalDate before = LocalDate.of(2003, 1, 1);
        LocalDate after = LocalDate.of(1995, 1, 1);

        List<Player> players =
                this.playerRepository.findByBirthDateBetweenOrderByStatShootingDescStatPassingDescStatEnduranceDescLastNameAsc(after,before);


        return players.stream().map(Player::toString).collect(Collectors.joining("\n"));
    }
}
