package softuni.exam.models.dto.Town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "to-town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownToDto {
    @XmlElement
    private String name;

    public TownToDto() {
    }

    public String getName() {
        return name;
    }
}
