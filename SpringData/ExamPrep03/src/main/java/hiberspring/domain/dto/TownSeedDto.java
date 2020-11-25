package hiberspring.domain.dto;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class TownSeedDto {

    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private Integer population;

}
