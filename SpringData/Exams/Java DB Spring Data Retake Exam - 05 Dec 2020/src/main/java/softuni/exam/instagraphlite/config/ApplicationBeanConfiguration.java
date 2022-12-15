package softuni.exam.instagraphlite.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ModelMapper modelMapper() {
        final ModelMapper modelMapper = new ModelMapper();

        modelMapper.addConverter(mappingContext ->
                        LocalDate.parse(mappingContext.getSource(), DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                String.class,
                LocalDate.class);

        return modelMapper;
    }

    @Bean
    public Validator createValidation() {
        return Validation
                .buildDefaultValidatorFactory()
                .getValidator();
    }
}
