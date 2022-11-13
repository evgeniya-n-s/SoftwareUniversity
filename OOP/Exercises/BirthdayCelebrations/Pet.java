package BirthdayCelebrations;

public class Pet implements Birthable,Creatures{
    private String name;
    private String birthDate;

    public Pet(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

@Override
    public String getName() {
        return name;
    }
@Override
    public String getBirthDate() {
        return birthDate;
    }

}
