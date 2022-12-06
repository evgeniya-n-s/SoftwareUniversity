package softuni.exam.models.dto.Forecast;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "forecasts")
@XmlAccessorType(XmlAccessType.FIELD)
public class ForecastImportWrapperDTO {
    @XmlElement(name = "forecast")
    private List<ForecastImportDTO> forecasts;

    public ForecastImportWrapperDTO() {
    }

    public List<ForecastImportDTO> getForecasts() {
        return forecasts;
    }
}
