package softuni.exam.models.dto.Car;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarIdDto {
    @XmlElement
    private Long id;

    public CarIdDto() {
    }

    public Long getId() {
        return id;
    }
}
