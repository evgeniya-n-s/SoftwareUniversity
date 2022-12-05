package softuni.exam.models.dto.Towns;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDTO {
    @Size(min = 2)
    private String townName;

    @Positive
    private int population;

    public TownImportDTO() {
    }

    public String getTownName() {
        return townName;
    }

    public int getPopulation() {
        return population;
    }
}
