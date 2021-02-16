package softuni.examprep.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {

    @NotBlank(message = "Enter username")
    @Size(min = 3,max = 20,message = "Username length must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Enter password")
    @Size(min = 3,max = 20,message = "Password length must be between 3 and 20 characters")
    private String password;

}
