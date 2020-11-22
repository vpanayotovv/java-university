package softuni.exam.models.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String make;
    @Column
    private String model;
    @Column
    private int kilometers;
    @Column
    private LocalDate registeredOn;

    @OneToMany(mappedBy = "car")
    private List<Picture> pictures;

    @OneToMany(mappedBy = "car")
    private List<Offer> offers;

}
