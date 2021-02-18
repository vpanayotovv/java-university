package com.examprep.andeys.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class UserRegisterBindingModel {

    @NotBlank(message = "Enter Username")
    @Size(min = 2,message = "Username length must be more than two characters")
    private String username;

    @NotBlank(message = "Enter Password")
    @Size(min = 2,message = "Password length must be more than two characters")
    private String password;

    @NotBlank(message = "Enter Password")
    @Size(min = 2,message = "Password length must be more than two characters")
    private String ConfirmPassword;

    @Email(message = "Enter valid Email")
    private String email;

    @DecimalMin(value = "0",message = "Budget must be a positive number")
    private BigDecimal budget;
}
