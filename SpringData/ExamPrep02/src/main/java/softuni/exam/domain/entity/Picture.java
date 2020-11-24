package softuni.exam.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "picture")
@Getter
@Setter
@NoArgsConstructor
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String url;

    @OneToMany(mappedBy = "picture")
    private List<Team> teams;

    @OneToMany(mappedBy = "picture")
    private List<Player> players;
}
