package softuni.exam.models.dto.Apartments;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentDTO {
    @XmlElement
    private Long id;

    public ApartmentDTO() {
    }

    public Long getId() {
        return id;
    }
}
