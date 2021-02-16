package softuni.examprep.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @Size(min = 3,max = 20,message = "Username length must be between 3 and 20 characters")
    private String username;

    @Size(min = 3,max = 20,message = "Password length must be between 3 and 20 characters")
    private String password;

    @Size(min = 3,max = 20,message = "Password length must be between 3 and 20 characters")
    private String confirmPassword;

    @Email
    private String email;

}
