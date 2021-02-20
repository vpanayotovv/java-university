package com.gira.giraexamprep.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @NotBlank(message = "Enter Username")
    @Length(min = 3 ,max = 20,message = "Username length must be between 3 and 20 characters")
    private String username;

    @NotBlank(message = "Enter Password")
    @Length(min = 3 ,max = 20,message = "Password length must be between 3 and 20 characters")
    private String password;

    @NotBlank(message = "Enter Password")
    @Length(min = 3 ,max = 20,message = "Password length must be between 3 and 20 characters")
    private String ConfirmPassword;

    @Email(message = "Enter valid Email")
    private String email;

}
