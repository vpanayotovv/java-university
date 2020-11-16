package com.example.automappingexercise.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GameAddDto {

    @Pattern(regexp = "^[A-Z].+",message = "Title not valid!")
    @Size(min = 3,max = 100,message = "Title not in proper size!")
    private String title;

    @DecimalMin(value = "0",message = "Value must be positive!")
    private BigDecimal price;

    @Min(value = 0, message = "Size must be positive!")
    private Double size;

    @Size(min = 11,max = 11, message = "Trailer have to be from YouTube")
    private String trailer;

    @Pattern(regexp = "http://.+|https://.+",message = "Link don't valid!")
    private String imageThumbnail;

    @Size(min = 20)
    private String description;

    private LocalDate releaseDate;

}
