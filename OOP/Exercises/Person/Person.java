package Person;

public class Person {
    private String firstName;
    private String lastName;
    private int age;
    private double salary;

    public Person(String firstName, String lastName, int age,double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.salary = salary;
    }

    public double getSalary() {
        return this.salary;
    }

    public void increaseSalary(double bonus){
        if(this.age < 30){
            bonus /= 2;
        }

        this.salary = this.salary*(1 + bonus/100);
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public int getAge() {
        return this.age;
    }

    @Override
    public String toString() {
        return String.format ( "%s %s gets %.1f leva",this.getFirstName (),this.getLastName (),this.getSalary () );
    }
}
