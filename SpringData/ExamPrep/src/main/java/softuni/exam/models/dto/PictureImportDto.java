package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class PictureImportDto {

    @Expose
    @Length(min = 2,max = 20)
    private String name;
    @Expose
    private LocalDateTime dateAndTime;
    @Expose
    private int car;

}
