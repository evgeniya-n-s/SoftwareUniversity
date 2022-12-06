package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.City.CityImportDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CityService;

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
public class CityServiceImpl implements CityService {
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository, CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, Validator validator) {
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.cityRepository.count()>0;
    }

    @Override
    public String readCitiesFileContent() throws IOException {
        return Files.readString(CITIES_JSON_PATH);
    }

    @Override
    public String importCities() throws IOException {
        String json = this.readCitiesFileContent();
        CityImportDTO[] cityImportDTOS = this.gson.fromJson(json, CityImportDTO[].class);
        return Arrays.stream(cityImportDTOS).map(this::importCity).collect(Collectors.joining("\n"));
    }

    private String importCity(CityImportDTO dto) {
        Set<ConstraintViolation<CityImportDTO>> validateErrors = this.validator.validate(dto);

        if(!validateErrors.isEmpty()){
            return "Invalid city";
        }

        Optional<City> checkCityName = this.cityRepository.findByCityName(dto.getCityName());
        if(checkCityName.isPresent()){
            return "Invalid city";
        }

        City city = this.modelMapper.map(dto,City.class);
        Optional<Country> country = this.countryRepository.findById(dto.getCountry());
        city.setCountry(country.get());

        this.cityRepository.save(city);
        String msg = String.format("Successfully imported city %s - %d",
                city.getCityName(), city.getPopulation());
        return msg;
    }
}
