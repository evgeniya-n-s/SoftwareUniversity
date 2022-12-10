package exam.model.dto.Customer;


import exam.model.dto.Town.TownNameDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class CustomerImportDto {
    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    @Email
    private String email;

    private String registeredOn;

    private TownNameDto town;

    public CustomerImportDto() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getRegisteredOn() {
        return registeredOn;
    }

    public TownNameDto getTown() {
        return town;
    }
}
