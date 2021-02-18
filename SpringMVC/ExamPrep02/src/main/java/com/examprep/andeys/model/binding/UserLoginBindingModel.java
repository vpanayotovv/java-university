package com.examprep.andeys.model.binding;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class UserLoginBindingModel {

    @NotBlank(message = "Enter Username")
    @Size(min = 2,message = "Username length must be more than two characters")
    private String username;

    @NotBlank(message = "Enter Username")
    @Size(min = 2,message = "Username length must be more than two characters")
    private String password;

}
