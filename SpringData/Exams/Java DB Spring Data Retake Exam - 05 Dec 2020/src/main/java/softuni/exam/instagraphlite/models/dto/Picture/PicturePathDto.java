package softuni.exam.instagraphlite.models.dto.Picture;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "picture")
@XmlAccessorType(XmlAccessType.FIELD)
public class PicturePathDto {
    @XmlElement(name = "path")
    private String path;

    public PicturePathDto() {
    }

    public String getPath() {
        return path;
    }
}
