package softuni.exam.models.dto.Apartments;

import softuni.exam.models.entity.enums.ApartmentType;

import javax.validation.constraints.Min;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class ApartmentImportDTO {
    @XmlElement
    private ApartmentType apartmentType;

    @XmlElement
    @Min(40)
    private double area;

    @XmlElement
    private String town;

    public ApartmentImportDTO() {
    }

    public ApartmentType getApartmentType() {
        return apartmentType;
    }

    public double getArea() {
        return area;
    }

    public String getTown() {
        return town;
    }
}
