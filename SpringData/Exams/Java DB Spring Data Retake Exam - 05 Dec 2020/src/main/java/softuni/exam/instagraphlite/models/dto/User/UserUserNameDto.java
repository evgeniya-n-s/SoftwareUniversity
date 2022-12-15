package softuni.exam.instagraphlite.models.dto.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserUserNameDto {
    @XmlElement(name = "username")
    private String userName;

    public UserUserNameDto() {
    }

    public String getUserName() {
        return userName;
    }
}
