package com.gira.giraexamprep.model.entity;

import com.gira.giraexamprep.model.entity.BaseEntity;
import com.gira.giraexamprep.model.entity.enums.ProgressName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@NoArgsConstructor
public class TaskEntity extends BaseEntity {

    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ProgressName progressName;
    private LocalDate dueDate;

    @ManyToOne
    private ClassificationEntity classification;

    @ManyToOne
    private UserEntity user;
}
