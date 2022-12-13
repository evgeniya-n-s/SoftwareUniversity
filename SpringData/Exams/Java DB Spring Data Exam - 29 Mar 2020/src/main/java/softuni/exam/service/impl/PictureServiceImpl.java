package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.Car;
import softuni.exam.models.Picture;
import softuni.exam.models.dto.Picture.PictureImportDto;
import softuni.exam.repository.CarRepository;
import softuni.exam.repository.PictureRepository;
import softuni.exam.service.PictureService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constans.Paths.PICTURES_JSON_PATH;

@Service
public class PictureServiceImpl implements PictureService {
    private final PictureRepository pictureRepository;
    private final CarRepository carRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;
    private final Gson gson;

    @Autowired
    public PictureServiceImpl(PictureRepository pictureRepository, CarRepository carRepository, ModelMapper modelMapper, Validator validator, Gson gson) {
        this.pictureRepository = pictureRepository;
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;
        this.gson = gson;
    }

    @Override
    public boolean areImported() {
        return this.pictureRepository.count()>0;
    }

    @Override
    public String readPicturesFromFile() throws IOException {
        return Files.readString(PICTURES_JSON_PATH);
    }

    @Override
    public String importPictures() throws IOException {
        String json = this.readPicturesFromFile();
        PictureImportDto[] pictureImportDtos = this.gson.fromJson(json, PictureImportDto[].class);

        return Arrays.stream(pictureImportDtos).map(this::importPicture).collect(Collectors.joining("\n"));
    }

    private String importPicture(PictureImportDto dto) {
        Set<ConstraintViolation<PictureImportDto>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid picture";
        }

        Optional<Car> checkCar = this.carRepository.findById(dto.getCar());
        if(!checkCar.isPresent()){
            return "Invalid picture";
        }

        Picture picture = this.modelMapper.map(dto, Picture.class);
        picture.setCar(checkCar.get());
        this.pictureRepository.save(picture);
        String message = String.format("Successfully import picture - %s",picture.getName());
        return message;
    }
}
