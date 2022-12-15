package softuni.exam.instagraphlite.models.dto.User;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserImportDto {
    @Size(min = 2,max = 18)
    @NotNull
    private String username;
    @Size(min = 4)
    private String password;

    private String profilePicture;

    public UserImportDto() {
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getProfilePicture() {
        return profilePicture;
    }
}
