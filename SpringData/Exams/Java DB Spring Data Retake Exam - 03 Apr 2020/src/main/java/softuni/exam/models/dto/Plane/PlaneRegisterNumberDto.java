package softuni.exam.models.dto.Plane;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "plane")
@XmlAccessorType(XmlAccessType.FIELD)
public class PlaneRegisterNumberDto {
    @XmlElement(name = "register-number")
    private String registerNumber;

    public PlaneRegisterNumberDto() {
    }

    public String getRegisterNumber() {
        return registerNumber;
    }
}
