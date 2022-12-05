package softuni.exam.models.dto.Agents;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

public class AgentImportDTO {
    @Size(min = 2)
    private String firstName;

    @Size(min = 2)
    private String lastName;

    private String town;

    @Email
    private String email;

    public AgentImportDTO() {
    }

    public String getTown() {
        return town;
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
}
