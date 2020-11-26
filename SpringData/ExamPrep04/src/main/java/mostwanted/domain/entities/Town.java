package mostwanted.domain.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "towns")
@Getter
@Setter
@NoArgsConstructor
public class Town extends BaseEntity{

    @Column(nullable = false,unique = true)
    private String name;

    @OneToMany(mappedBy = "town")
    private Set<District> districts;

    @OneToMany(mappedBy = "town")
    private Set<Racer> racers;

}
