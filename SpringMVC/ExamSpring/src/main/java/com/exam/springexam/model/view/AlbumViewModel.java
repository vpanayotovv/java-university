package com.exam.springexam.model.view;

import com.exam.springexam.model.entity.ArtistEntity;
import com.exam.springexam.model.entity.enums.GenreName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class AlbumViewModel {

    private Long id;

    private String imageUrl;

    private String name;

    private ArtistEntity artist;

    private GenreName genre;

    private BigDecimal price;

    private LocalDate releasedDate;

    private Long copies;
}
