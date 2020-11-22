package softuni.jsonexercise.domain.dtos;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class SaleExposeDto {

    @Expose
    private Long id;

    @Expose
    private int discount;
}
