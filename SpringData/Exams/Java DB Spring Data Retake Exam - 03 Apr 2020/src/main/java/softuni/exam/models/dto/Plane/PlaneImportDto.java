package softuni.exam.models.dto.Plane;

import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneImportDto {
    @XmlElement(name = "register-number")
    @Size(min = 5)
    private String registerNumber;
    @XmlElement
    @Positive
    private int capacity;
    @XmlElement
    @Size(min = 2)
    private String airline;

    public PlaneImportDto() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getAirline() {
        return airline;
    }
}