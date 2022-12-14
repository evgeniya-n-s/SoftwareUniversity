package softuni.exam.models.dto.Town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "from-town")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownFromDto {
    @XmlElement
    private String name;

    public TownFromDto() {
    }

    public String getName() {
        return name;
    }
}
