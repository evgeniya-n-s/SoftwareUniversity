package softuni.exam.models.dto.Car;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class CarImportDto {
    @Size(min = 2,max = 20)
    private String make;

    @Size(min = 2, max = 20)
    private String model;

    @Positive
    private int kilometers;

    private String registeredOn;

    public CarImportDto() {
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }
}
