package softuni.library.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class AuthorsSeedDto {

    @Expose
    @NotNull
    @Length(min = 2)
    private String firstName;

    @Expose
    @NotNull
    @Length(min = 2)
    private String lastName;

    @Expose
    @NotNull
    @Length(min = 3,max = 12)
    private String birthTown;


}
