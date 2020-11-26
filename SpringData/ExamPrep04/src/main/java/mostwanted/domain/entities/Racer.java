package mostwanted.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "racers")
@Getter
@Setter
@NoArgsConstructor
public class Racer extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;

    @Column
    private Integer age;

    @Column
    private BigDecimal bounty;

    @ManyToOne
    private Town town;

    @OneToMany(mappedBy = "racer")
    private Set<Car> cars;

    @OneToMany(mappedBy = "racer")
    private Set<RaceEntry> raceEntries;
}
