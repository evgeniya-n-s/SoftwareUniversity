package softuni.exam.models.dto.Ticket;

import softuni.exam.models.dto.Passenger.PassengerEmailDto;
import softuni.exam.models.dto.Plane.PlaneRegisterNumberDto;
import softuni.exam.models.dto.Town.TownFromDto;
import softuni.exam.models.dto.Town.TownToDto;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "ticket")
@XmlAccessorType(XmlAccessType.FIELD)
public class TicketImportDto {
    @XmlElement(name = "serial-number")
    @Size(min = 2)
    private String serialNumber;
    @XmlElement
    @Positive
    private BigDecimal price;
    @XmlElement(name = "take-off")
    private String takeOff;
    @XmlElement(name = "from-town")
    private TownFromDto townFrom;
    @XmlElement(name = "to-town")
    private TownToDto townTo;
    @XmlElement(name = "passenger")
    private PassengerEmailDto passenger;

    @XmlElement(name = "plane")
    private PlaneRegisterNumberDto plane;

    public TicketImportDto() {
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getTakeOff() {
        return takeOff;
    }

    public TownFromDto getTownFrom() {
        return townFrom;
    }

    public TownToDto getTownTo() {
        return townTo;
    }

    public PassengerEmailDto getPassenger() {
        return passenger;
    }

    public PlaneRegisterNumberDto getPlane() {
        return plane;
    }
}
