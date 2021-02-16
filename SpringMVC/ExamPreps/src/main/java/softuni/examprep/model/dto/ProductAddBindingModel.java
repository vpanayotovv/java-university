package softuni.examprep.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import softuni.examprep.model.entity.enums.CategoryName;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ProductAddBindingModel {

    @NotBlank(message = "Cannot be empty")
    @Size(min = 3,max = 20,message = "Name length must be between 3 and 20 characters")
    private String name;

    @Size(min = 5,message = "Description min length must be minimum 5")
    private String description;

    @DecimalMin(value = "0",message = "Must be positive")
    private BigDecimal price;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @FutureOrPresent(message = "Cannot be in past")
    private LocalDateTime neededBefore;

    @NotNull(message = "Must select Category")
    private CategoryName category;

}
