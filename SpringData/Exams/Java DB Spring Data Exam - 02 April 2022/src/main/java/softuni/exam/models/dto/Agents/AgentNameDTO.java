package softuni.exam.models.dto.Agents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "agent")
@XmlAccessorType(XmlAccessType.FIELD)
public class AgentNameDTO {
    @XmlElement
    private String name;

    public AgentNameDTO() {
    }

    public String getName() {
        return name;
    }
}
