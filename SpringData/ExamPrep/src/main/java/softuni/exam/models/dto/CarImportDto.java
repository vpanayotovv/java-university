package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class CarImportDto {

    @Expose
    @Length(min = 2,max = 20)
    private String make;

    @Expose
    @Length(min = 2,max = 20)
    private String model;

    @Expose
    @Min(value = 0)
    private int kilometers;

    @Expose
    private LocalDate registeredOn;
}
