package softuni.library.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookSeedDto {

    @Expose
    @Length(min = 100)
    private String description;

    @Expose
    @Min(1)
    @Max(5)
    private int edition;

    @Expose
    @NotNull
    @Length(min = 5)
    private String name;

    @Expose
    @NotNull
    private LocalDate written;

    @Expose
    private int author;
}
