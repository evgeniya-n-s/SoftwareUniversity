package softuni.exam.models.dto.Offers;

import softuni.exam.models.dto.Agents.AgentNameDTO;
import softuni.exam.models.dto.Apartments.ApartmentDTO;

import javax.validation.constraints.Positive;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "offer")
@XmlAccessorType(XmlAccessType.FIELD)
public class OfferImportDTO {
    @XmlElement
    @Positive
    private BigDecimal price;

    @XmlElement(name = "agent")
    private AgentNameDTO agent;

    @XmlElement(name = "apartment")
    private ApartmentDTO apartment;

    @XmlElement
    private String publishedOn;

    public OfferImportDTO() {
    }

    public BigDecimal getPrice() {
        return price;
    }

    public AgentNameDTO getAgent() {
        return agent;
    }

    public ApartmentDTO getApartment() {
        return apartment;
    }

    public String getPublishedOn() {
        return publishedOn;
    }
}
