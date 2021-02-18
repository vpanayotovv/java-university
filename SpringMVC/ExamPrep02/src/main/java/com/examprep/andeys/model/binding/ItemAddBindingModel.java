package com.examprep.andeys.model.binding;

import com.examprep.andeys.model.entity.enums.CategoryName;
import com.examprep.andeys.model.entity.enums.GenderName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class ItemAddBindingModel {

    @Size(min = 3,message = "Name length must be more than two characters")
    @NotBlank(message = "Enter name")
    private String name;

    @Size(min = 4,message = "Description length must be more than two characters")
    private String description;

    @NotNull(message = "Select category")
    private CategoryName category;

    @NotNull(message = "Select gender")
    private GenderName gender;

    @DecimalMin(value = "0",message = "Price must be positive number")
    private BigDecimal price;

}
