package _04_HospitalDatabase.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "_04_diagnoses")
public class Diagnose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    private String comments;

    @ManyToMany(mappedBy = "diagnose")
    private Set<Patient> patients;

    public Diagnose() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
