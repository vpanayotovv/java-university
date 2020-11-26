package mostwanted.domain.entities;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "race_entries")
@Getter
@Setter
@NoArgsConstructor
public class RaceEntry extends BaseEntity{

    @Column
    private boolean hasFinished;

    @Column
    private Double finishTime;

    @ManyToOne
    private Car car;

    @ManyToOne
    private Racer racer;

    @ManyToOne
    private Race race;
}
