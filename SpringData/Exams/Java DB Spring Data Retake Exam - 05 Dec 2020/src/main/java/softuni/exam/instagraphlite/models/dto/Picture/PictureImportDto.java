package softuni.exam.instagraphlite.models.dto.Picture;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class PictureImportDto {
    @NotNull
    private String path;

    @Min(500)
    @Max(60000)
    private double size;

    public PictureImportDto() {
    }

    public String getPath() {
        return path;
    }

    public double getSize() {
        return size;
    }
}
