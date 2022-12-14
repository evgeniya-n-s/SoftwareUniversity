package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Town;
import softuni.exam.models.dto.Town.TownImportDto;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.TownService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constants.Paths.TOWNS_JSON_PATH;

@Service
public class TownServiceImpl implements TownService {
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public TownServiceImpl(TownRepository townRepository, ModelMapper modelMapper, Validator validator, Gson gson) {
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.townRepository.count()>0;
    }

    @Override
    public String readTownsFileContent() throws IOException {
        return Files.readString(TOWNS_JSON_PATH);
    }

    @Override
    public String importTowns() throws IOException {
        String json = this.readTownsFileContent();
        TownImportDto[] townImportDtos = this.gson.fromJson(json, TownImportDto[].class);

        return Arrays.stream(townImportDtos).map(this::importTown).collect(Collectors.joining("\n"));
    }

    private String importTown(TownImportDto dto) {
        Set<ConstraintViolation<TownImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Town";
        }
        Town town = this.modelMapper.map(dto, Town.class);
        this.townRepository.save(town);

        String message = String.format("Successfully imported Town %s - %d",town.getName(),town.getPopulation());
        return message;
    }
}
