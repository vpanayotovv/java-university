package com.gira.giraexamprep.model.binding;

import com.gira.giraexamprep.model.entity.UserEntity;
import com.gira.giraexamprep.model.entity.enums.ClassificationName;
import com.gira.giraexamprep.model.entity.enums.ProgressName;
import com.gira.giraexamprep.model.service.UserServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TaskAddBindingModel {

    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters")
    private String name;

    @Size(min = 4,message = "Description min length must be minimum 5 characters")
    private String description;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDate dueDate;

    @NotNull(message = "Select classification")
    private ClassificationName classification;

    private UserServiceModel userServiceModel;

}
