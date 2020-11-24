package softuni.exam.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "team")
@Getter
@Setter
@NoArgsConstructor
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Picture picture;

    @OneToMany(mappedBy = "team")
    private List<Player> players;


}
