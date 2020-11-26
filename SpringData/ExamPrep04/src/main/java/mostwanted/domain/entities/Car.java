package mostwanted.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "cars")
@Getter
@Setter
@NoArgsConstructor
public class Car extends BaseEntity{

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column
    private BigDecimal price;

    @Column(nullable = false)
    private Integer yearOfProduction;

    @Column
    private Double maxSpeed;

    @Column
    private Double zeroToSixty;

    @ManyToOne
    private Racer racer;

    @OneToMany(mappedBy = "car")
    private Set<RaceEntry> raceEntries;
}
