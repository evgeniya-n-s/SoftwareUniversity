package softuni.exam.instagraphlite.models.dto.Post;

import softuni.exam.instagraphlite.models.dto.Picture.PicturePathDto;
import softuni.exam.instagraphlite.models.dto.User.UserUserNameDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "post")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostImportDto {
    @XmlElement
    @Size(min = 21)
    @NotNull
    private String caption;

    @XmlElement(name = "user")
    private UserUserNameDto user;
    @XmlElement(name = "picture")
    private PicturePathDto picture;

    public PostImportDto() {
    }

    public String getCaption() {
        return caption;
    }

    public UserUserNameDto getUser() {
        return user;
    }

    public PicturePathDto getPicture() {
        return picture;
    }
}
