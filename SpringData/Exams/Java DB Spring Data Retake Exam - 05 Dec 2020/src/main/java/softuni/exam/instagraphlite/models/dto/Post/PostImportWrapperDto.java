package softuni.exam.instagraphlite.models.dto.Post;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "posts")
@XmlAccessorType(XmlAccessType.FIELD)
public class PostImportWrapperDto {
    @XmlElement(name = "post")
    private List<PostImportDto> posts;

    public PostImportWrapperDto() {
    }

    public List<PostImportDto> getPosts() {
        return posts;
    }
}
