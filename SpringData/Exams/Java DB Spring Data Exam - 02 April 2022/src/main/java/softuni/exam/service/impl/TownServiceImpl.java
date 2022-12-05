package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Towns.TownImportDTO;
import softuni.exam.models.entity.Town;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constants.Paths.*;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Gson gson, Validator validator) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count() >0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(TOWNS_JSON_PATH);
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();
        TownImportDTO[] townImportDTOS = this.gson.fromJson(json, TownImportDTO[].class);

        return Arrays.stream(townImportDTOS).map(this::importTown).collect(Collectors.joining("\n"));
    }

    private String importTown(TownImportDTO dto) {
        Set<ConstraintViolation<TownImportDTO>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid town";
        }

        Optional<Town> checkTown = this.townRepository.findByTownName(dto.getTownName());
        if(checkTown.isPresent()){
            return "Town name already exists";
        }

        Town town = this.modelMapper.map(dto, Town.class);
        this.townRepository.save(town);
        String message = String.format("Successfully imported town %s - %d",town.getTownName(),town.getPopulation());
        return message;
    }
}
