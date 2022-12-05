package softuni.exam.models.dto.Apartments;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "apartments")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentImportWrapperDTO {
    @XmlElement(name = "apartment")
    private List<ApartmentImportDTO> apartments;

    public ApartmentImportWrapperDTO() {
    }

    public List<ApartmentImportDTO> getApartments() {
        return apartments;
    }
}
