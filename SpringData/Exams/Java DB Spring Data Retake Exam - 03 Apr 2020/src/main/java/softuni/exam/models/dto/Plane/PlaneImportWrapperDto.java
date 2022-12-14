package softuni.exam.models.dto.Plane;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "planes")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneImportWrapperDto {
    @XmlElement(name = "plane")
    private List<PlaneImportDto> planes;

    public PlaneImportWrapperDto() {
    }

    public List<PlaneImportDto> getPlanes() {
        return planes;
    }
}
