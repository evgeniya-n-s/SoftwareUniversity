package softuni.exam.models.dto.Picture;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PictureImportDto {
    @Size(min = 2,max = 20)
    private String name;
    private String dateAndTime;
    private Long car;

    public PictureImportDto() {
    }

    public String getName() {
        return name;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public Long getCar() {
        return car;
    }
}
