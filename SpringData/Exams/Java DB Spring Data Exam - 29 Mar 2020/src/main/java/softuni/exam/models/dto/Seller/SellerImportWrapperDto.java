package softuni.exam.models.dto.Seller;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;


@XmlRootElement(name = "sellers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SellerImportWrapperDto {
    @XmlElement(name = "seller")
    private List<SellerImportDto> seller;

    public SellerImportWrapperDto() {
    }

    public List<SellerImportDto> getSeller() {
        return seller;
    }
}
