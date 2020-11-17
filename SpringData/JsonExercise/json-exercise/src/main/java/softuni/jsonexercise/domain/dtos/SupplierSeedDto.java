package softuni.jsonexercise.domain.dtos;


import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SupplierSeedDto {
    @Expose
    private String name;

    @Expose
    private boolean isImporter;
}
