package com.example.automappingexercise.domain.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "games")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title",nullable = false)
    private String title;
    private String trailer;
    private String imageThumbnail;
    private Double size;
    private BigDecimal price;
    private String description;
    private LocalDate releaseDate;
    @ManyToMany(mappedBy = "games")
    private List<User> users;

}
