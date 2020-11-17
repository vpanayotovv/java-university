package softuni.jsonexercise.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CarSeedDto {

    @Expose
    private String make;

    @Expose
    private String model;

    @Expose
    private Long travelledDistance;

}
