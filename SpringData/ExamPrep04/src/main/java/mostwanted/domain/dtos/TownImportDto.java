package mostwanted.domain.dtos;


import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TownImportDto {

    @Expose
    @NotNull
    private String name;

}
