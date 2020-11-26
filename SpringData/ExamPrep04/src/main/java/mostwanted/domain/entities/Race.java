package mostwanted.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "races")
@Getter
@Setter
@NoArgsConstructor
public class Race extends BaseEntity{

    @Column(nullable = false,columnDefinition = "int default 0")
    private int laps;

    @ManyToOne
    private District district;
}
