package com.examprep.andeys.model.entity;

import com.examprep.andeys.model.entity.enums.GenderName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "items")
@Getter
@Setter
@NoArgsConstructor
public class Item extends BaseEntity {


    @Column(nullable = false,unique = true)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    private Category category;

    @Enumerated(EnumType.STRING)
    private GenderName gender;

}
