package softuni.exam.models.dto.Seller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "seller")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerIdDto {
    @XmlElement
    private Long id;

    public SellerIdDto() {
    }

    public Long getId() {
        return id;
    }
}
