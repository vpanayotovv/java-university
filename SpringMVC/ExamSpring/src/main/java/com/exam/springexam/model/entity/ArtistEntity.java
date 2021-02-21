package com.exam.springexam.model.entity;

import com.exam.springexam.model.entity.enums.ArtistName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "artists")
@Getter
@Setter
@NoArgsConstructor
public class ArtistEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ArtistName name;

    @Column(columnDefinition = "TEXT")
    private String careerInformation;

}
