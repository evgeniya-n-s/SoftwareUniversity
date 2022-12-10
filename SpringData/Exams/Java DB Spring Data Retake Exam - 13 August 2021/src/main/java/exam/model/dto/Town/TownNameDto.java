package exam.model.dto.Town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownNameDto {
    @XmlElement
    private String name;

    public TownNameDto() {
    }

    public String getName() {
        return name;
    }
}
