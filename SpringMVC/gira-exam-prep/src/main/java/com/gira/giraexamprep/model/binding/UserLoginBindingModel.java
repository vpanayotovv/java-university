package com.gira.giraexamprep.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {

    @NotBlank(message = "Email cannot be empty")
    private String email;

    @NotBlank(message = "Enter Password")
    @Length(min = 3 ,max = 20,message = "Password length must be between 3 and 20 characters")
    private String password;

}
