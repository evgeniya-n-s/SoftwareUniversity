package softuni.exam.models.dto.Passenger;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "passenger")
@XmlAccessorType(XmlAccessType.FIELD)
public class PassengerEmailDto {
    @XmlElement
    private String email;

    public PassengerEmailDto() {
    }

    public String getEmail() {
        return email;
    }
}
