package Person;

public class Student extends Person{
    public Student(String firstName, String lastName, int age, double salary) {
        super ( firstName, lastName, age, salary );
    }

    @Override
    public String toString() {
        return "Student" + super.getFirstName () + " "
                + this.getLastName () + " " + getAge ();
    }
}
