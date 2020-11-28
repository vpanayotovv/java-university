package mostwanted.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class CarImportDto {

    @Expose
    @NotNull
    private String brand;

    @Expose
    @NotNull
    private String model;

    @Expose
    private BigDecimal price;

    @Expose
    @NotNull
    private Integer yearOfProduction;

    @Expose
    private Double maxSpeed;

    @Expose
    private Double zeroToSixty;

    @Expose
    private String racerName;

}
