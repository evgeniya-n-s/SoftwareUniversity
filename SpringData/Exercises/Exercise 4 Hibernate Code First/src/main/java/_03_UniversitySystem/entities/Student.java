package _03_UniversitySystem.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "_03_students")
public class Student extends Person {
    @Column(name = "average_grade", nullable = false)
    private float averageGrade;
    private int attendance;

    @ManyToMany(mappedBy = "students")
    private Set<Course> courses;

    public Student() {

    }


    public float getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(float averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getAttendance() {
        return attendance;
    }

    public void setAttendance(int attendance) {
        this.attendance = attendance;
    }
}
