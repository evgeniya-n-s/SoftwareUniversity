package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Passenger;
import softuni.exam.models.Town;
import softuni.exam.models.dto.Passenger.PassengerImportDto;
import softuni.exam.repository.PassengerRepository;
import softuni.exam.repository.TownRepository;
import softuni.exam.service.PassengerService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constants.Paths.PASSENGERS_JSON_PATH;

@Service
public class PassengerServiceImpl implements PassengerService {
    private final PassengerRepository passengerRepository;
    private final TownRepository townRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public PassengerServiceImpl(PassengerRepository passengerRepository, TownRepository townRepository, ModelMapper modelMapper, Validator validator, Gson gson) {
        this.passengerRepository = passengerRepository;
        this.townRepository = townRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.passengerRepository.count()>0;
    }

    @Override
    public String readPassengersFileContent() throws IOException {
        return Files.readString(PASSENGERS_JSON_PATH);
    }

    @Override
    public String importPassengers() throws IOException {
        String json = this.readPassengersFileContent();
        PassengerImportDto[] passengerImportDtos = this.gson.fromJson(json, PassengerImportDto[].class);

        return Arrays.stream(passengerImportDtos).map(this::importPassenger).collect(Collectors.joining("\n"));
    }

    private String importPassenger(PassengerImportDto dto) {
        Set<ConstraintViolation<PassengerImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid Passenger";
        }

        Optional<Town> checkTownName = this.townRepository.findTownByName(dto.getTown());
        if(!checkTownName.isPresent()){
            return "Invalid Town name";
        }

        Passenger passenger = this.modelMapper.map(dto, Passenger.class);
        passenger.setTown(checkTownName.get());
        this.passengerRepository.save(passenger);

        String message = String.format("Successfully imported Passenger %s - %s",passenger.getLastName(),passenger.getEmail());
        return message;
    }

    @Override
    public String getPassengersOrderByTicketsCountDescendingThenByEmail() {
        List<Passenger> passengers=this.passengerRepository.findAllByOrderByTicketsDescEmailAsc();
        return passengers.stream().map(Passenger::toString).collect(Collectors.joining("\n"));
    }
}
