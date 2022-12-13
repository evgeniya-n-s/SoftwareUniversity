package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.dto.Car.CarImportDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.service.CarService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constans.Paths.CARS_JSON_PATH;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, ModelMapper modelMapper, Validator validator, Gson gson) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.carRepository.count()>0;
    }

    @Override
    public String readCarsFileContent() throws IOException {
        return Files.readString(CARS_JSON_PATH);
    }

    @Override
    public String importCars() throws IOException {
        String json = this.readCarsFileContent();
        CarImportDto[] carImportDtos = this.gson.fromJson(json, CarImportDto[].class);

        return Arrays.stream(carImportDtos).map(this::importCar).collect(Collectors.joining("\n"));
    }

    private String importCar(CarImportDto dto) {
        Set<ConstraintViolation<CarImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid car";
        }
        Optional<Car> checkCarMake = this.carRepository.findCarByMake(dto.getMake());
        if(checkCarMake.isPresent()){
            return "Invalid car";
        }

        Car car = this.modelMapper.map(dto, Car.class);
        this.carRepository.save(car);
        String message = String.format("Successfully imported car - %s - %s",car.getMake(),car.getModel());
        return message;
    }

    @Override
    public String getCarsOrderByPicturesCountThenByMake() {
        List<Car> cars = this.carRepository.findAllByOrderByPicturesDescMakeAsc();
        return cars.stream().map(Car::toString).collect(Collectors.joining("\n"));
    }
}
