package softuni.exam.models.dto.City;

import softuni.exam.models.entity.Country;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class CityImportDTO {
    @Size(min = 2,max = 60)
    private String cityName;

    @Size(min = 2)
    private String description;

    @Min(500)
    private int population;

    private Long country;

    public CityImportDTO() {
    }

    public String getCityName() {
        return cityName;
    }

    public String getDescription() {
        return description;
    }

    public int getPopulation() {
        return population;
    }

    public Long getCountry() {
        return country;
    }
}
