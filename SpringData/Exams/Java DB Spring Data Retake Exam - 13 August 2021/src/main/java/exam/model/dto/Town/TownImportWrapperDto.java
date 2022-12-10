package exam.model.dto.Town;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "towns")
@XmlAccessorType(XmlAccessType.FIELD)
public class TownImportWrapperDto {
    @XmlElement(name = "town")
    private List<TownImportDto> towns;

    public TownImportWrapperDto() {
    }

    public List<TownImportDto> getTowns() {
        return towns;
    }
}
