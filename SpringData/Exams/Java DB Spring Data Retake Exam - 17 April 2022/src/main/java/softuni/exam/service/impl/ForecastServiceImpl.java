package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.Forecast.ForecastImportDTO;
import softuni.exam.models.dto.Forecast.ForecastImportWrapperDTO;
import softuni.exam.models.entity.City;
import softuni.exam.models.entity.DayOfWeek;
import softuni.exam.models.entity.Forecast;
import softuni.exam.repository.CityRepository;
import softuni.exam.repository.ForecastRepository;
import softuni.exam.service.ForecastService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static softuni.exam.constants.Paths.*;

@Service
public class ForecastServiceImpl implements ForecastService {
    private final ForecastRepository forecastRepository;
    private final CityRepository cityRepository;
    private final ModelMapper modelMapper;
    private final Validator validator;

    private final Unmarshaller unmarshaller;

    @Autowired
    public ForecastServiceImpl(ForecastRepository forecastRepository, CityRepository cityRepository, ModelMapper modelMapper, Validator validator) throws JAXBException {
        this.forecastRepository = forecastRepository;
        this.cityRepository = cityRepository;
        this.modelMapper = modelMapper;
        this.validator = validator;

        JAXBContext context = JAXBContext.newInstance(ForecastImportWrapperDTO.class);
        this.unmarshaller = context.createUnmarshaller();
    }

    @Override
    public boolean areImported() {
        return this.forecastRepository.count()>0;
    }

    @Override
    public String readForecastsFromFile() throws IOException {
        return Files.readString(FORECASTS_XML_PATH);
    }

    @Override
    public String importForecasts() throws IOException, JAXBException {
        FileReader fileReader = new FileReader(FORECASTS_XML_PATH.toFile());
        ForecastImportWrapperDTO forecastDTO =
                (ForecastImportWrapperDTO)this.unmarshaller.unmarshal(fileReader);

        return forecastDTO.getForecasts().stream().map(this::importForecast).collect(Collectors.joining("\n"));
    }

    private String importForecast(ForecastImportDTO dto) {
        Set<ConstraintViolation<ForecastImportDTO>> validateErrors = this.validator.validate(dto);
        if(!validateErrors.isEmpty()){
            return "Invalid forecast";
        }

        Optional<Forecast> forecastExist =
                this.forecastRepository.findByCity_IdAndDayOfWeek(dto.getCity(), dto.getDayOfWeek());

        Optional<City> city = this.cityRepository.findById(dto.getCity());

        if (forecastExist.isPresent() || city.isEmpty()) {
            return "Invalid forecast";
        }

        Forecast forecast = this.modelMapper.map(dto, Forecast.class);
        forecast.setCity(city.get());

        this.forecastRepository.save(forecast);
        String msg = String.format("Successfully import forecast %s - %f",
                forecast.getDayOfWeek().toString(), forecast.getMaxTemperature());
        return msg;
    }

    @Override
    public String exportForecasts() {
        int citizens = 150000;
        Optional<List<Forecast>> forecasts =
                this.forecastRepository.findAllByDayOfWeekAndCity_PopulationLessThanOrderByMaxTemperatureDescIdAsc(DayOfWeek.SUNDAY,citizens);

        List<Forecast> forecast = forecasts.orElseThrow(NoSuchElementException::new);

        return forecast.stream().map(Forecast::toString).collect(Collectors.joining("\n"));
    }
}
