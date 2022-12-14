package softuni.exam.models.dto.Town;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class TownImportDto {
    @Size(min = 2)
    private String name;

    @Positive
    private int population;

    private String guide;

    public TownImportDto() {
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getGuide() {
        return guide;
    }
}
