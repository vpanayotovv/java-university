package com.gira.giraexamprep.model.entity;

import com.gira.giraexamprep.model.entity.enums.ClassificationName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "classifications")
@Getter
@Setter
@NoArgsConstructor
public class ClassificationEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private ClassificationName classificationName;

    @Column
    private String description;
}
