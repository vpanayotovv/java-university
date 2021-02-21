package com.exam.springexam.model.binding;

import com.exam.springexam.model.entity.UserEntity;
import com.exam.springexam.model.entity.enums.ArtistName;
import com.exam.springexam.model.entity.enums.GenreName;
import com.exam.springexam.model.service.UserServiceModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AlbumAddBindingModel {

    @NotBlank(message = "Enter Name")
    @Size(min = 3 ,max = 20,message = "Name length must be between 3 and 20 characters")
    private String name;

    @NotBlank(message = "Enter imageUrl")
    @Size(min = 5 ,message = "Image Url length must be minimum 5 characters")
    private String imageUrl;

    @DecimalMin(value = "0",message = "Price must be positive")
    private BigDecimal price;

    @Min(value = 10,message = "Must be a more than 10")
    private Long copies;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent(message = "Release date cannot be in the future")
    private LocalDate releasedDate;

    private String producer;

    @NotNull(message = "Select Artist")
    private ArtistName artist;

    @NotNull(message = "Select Genre")
    private GenreName genre;

    @NotBlank(message = "Enter description")
    @Size(min = 5 ,message = "Description length must be minimum 5 characters")
    private String description;

    private UserServiceModel addedFrom;

}
