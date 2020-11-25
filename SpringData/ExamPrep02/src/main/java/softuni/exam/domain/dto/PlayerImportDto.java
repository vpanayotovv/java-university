package softuni.exam.domain.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class PlayerImportDto {

    @NotNull
    @Expose
    private String firstName;

    @NotNull
    @Expose
    @Length(min = 3,max = 15)
    private String lastName;

    @NotNull
    @Expose
    @Min(value = 1)
    @Max(value = 99)
    private int number;

    @NotNull
    @Expose
    @DecimalMin(value = "0")
    private BigDecimal salary;

    @NotNull
    @Expose
    private String position;

    @NotNull
    @Expose
    private PictureImportDto picture;

    @NotNull
    @Expose
    private TeamImportDto team;

}
