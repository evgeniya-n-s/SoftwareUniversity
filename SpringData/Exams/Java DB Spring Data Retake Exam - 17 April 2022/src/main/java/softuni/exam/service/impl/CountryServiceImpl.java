package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Country.CountryImportDTO;
import softuni.exam.models.entity.Country;
import softuni.exam.repository.CountryRepository;
import softuni.exam.service.CountryService;

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
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final ModelMapper modelMapper;
    private final Gson gson;
    private final Validator validator;

    @Autowired
    public CountryServiceImpl(CountryRepository countryRepository, ModelMapper modelMapper, Gson gson, Validator validator) {
        this.countryRepository = countryRepository;
        this.modelMapper = modelMapper;
        this.gson = gson;
        this.validator = validator;
    }

    @Override
    public boolean areImported() {
        return this.countryRepository.count() >0;
    }

    @Override
    public String readCountriesFromFile() throws IOException {
        return Files.readString(COUNTRIES_JSON_PATH);
    }

    @Override
    public String importCountries() throws IOException {
        String json = this.readCountriesFromFile();
        CountryImportDTO[] countryImportDTOS = this.gson.fromJson(json, CountryImportDTO[].class);

        return Arrays.stream(countryImportDTOS).map(this::importCountry).collect(Collectors.joining("\n"));
    }

    private String importCountry(CountryImportDTO dto) {
        Set<ConstraintViolation<CountryImportDTO>> validateErrors = this.validator.validate(dto);
        if (!validateErrors.isEmpty()){
            return "Invalid country";
        }

        Optional<Country> checkCountryName = this.countryRepository.findByCountryName(dto.getCountryName());
        if(checkCountryName.isPresent()){
            return "Country name already exists";
        }

        Country country = this.modelMapper.map(dto,Country.class);
        this.countryRepository.saveAndFlush(country);

        String msg = String.format("Successfully imported country %s - %s",
                country.getCountryName(), country.getCurrency());
        return msg;
    }
}
