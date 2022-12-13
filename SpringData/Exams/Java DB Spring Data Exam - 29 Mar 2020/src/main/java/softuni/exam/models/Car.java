package softuni.exam.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String make;

    @Column(unique = true)
    private String model;

    @Column(unique = true)
    private int kilometers;

    @Column(name = "registered_on")
    private LocalDate registeredOn;

    @OneToMany(targetEntity = Picture.class,mappedBy = "car",fetch = FetchType.EAGER)
    private List<Picture> pictures;

    public Car() {
        this.pictures=new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getKilometers() {
        return kilometers;
    }

    public void setKilometers(int kilometers) {
        this.kilometers = kilometers;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public void setRegisteredOn(LocalDate registeredOn) {
        this.registeredOn = registeredOn;
    }

    @Override
    public String toString() {
        return String.format("Car make - %s, model - %s\n" +
                "Kilometers - %d\n" +
                "Registered on - %tY-%tm-%td\n"+
                "Number of pictures - %d\n",
                this.make,
                this.model,
                this.kilometers,
                this.registeredOn,this.registeredOn,this.registeredOn,
                this.pictures.size());
    }
}
