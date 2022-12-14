package softuni.exam.models.dto.Passenger;

import javax.validation.constraints.Email;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class PassengerImportDto {
    @Size(min = 2)
    private String firstName;
    @Size(min = 2)
    private String lastName;
    @Positive
    private int age;
    private String phoneNumber;
    @Email
    private String email;
    private String town;

    public PassengerImportDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getTown() {
        return town;
    }
}
