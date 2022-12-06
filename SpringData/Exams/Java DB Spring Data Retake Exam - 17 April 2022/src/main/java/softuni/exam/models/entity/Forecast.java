package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "forecasts")
public class Forecast {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week",nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "max_temperature",nullable = false)
    private float maxTemperature;

    @Column(name = "min_temperature",nullable = false)
    private float minTemperature;

    @Column(nullable = false)
    private LocalTime sunrise;

    @Column(nullable = false)
    private LocalTime sunset;

    @ManyToOne
    private City city;

    public Forecast() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public float getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(float maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public float getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(float minTemperature) {
        this.minTemperature = minTemperature;
    }

    public LocalTime getSunrise() {
        return sunrise;
    }

    public void setSunrise(LocalTime sunrise) {
        this.sunrise = sunrise;
    }

    public LocalTime getSunset() {
        return sunset;
    }

    public void setSunset(LocalTime sunset) {
        this.sunset = sunset;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format("City: %s:\n" +
                "-min temperature: %f\n" +
                "--max temperature: %f\n" +
                "---sunrise: %tT\n" +
                "----sunset: %tT\n",
                this.getCity().getCityName(),
                this.minTemperature,
                this.maxTemperature,
                this.sunrise,
                this.sunset);
    }
}
