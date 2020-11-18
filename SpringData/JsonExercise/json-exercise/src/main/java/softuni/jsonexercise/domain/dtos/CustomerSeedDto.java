package softuni.jsonexercise.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerSeedDto {

    @Expose
    private String name;

    @Expose
    private LocalDateTime birthDate;

    @Expose
    private boolean isYoungDriver;
}
