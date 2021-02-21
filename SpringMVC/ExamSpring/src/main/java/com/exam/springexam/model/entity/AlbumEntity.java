package com.exam.springexam.model.entity;

import com.exam.springexam.model.entity.enums.GenreName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "albums")
@Getter
@Setter
@NoArgsConstructor
public class AlbumEntity extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String imageUrl;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Long copies;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private LocalDate releasedDate;

    private String producer;

    @Enumerated(EnumType.ORDINAL)
    private GenreName genre;

    @OneToOne
    private ArtistEntity artist;

    @ManyToOne
    private UserEntity addedFrom;
}
