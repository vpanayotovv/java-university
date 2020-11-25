package softuni.exam.domain.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "player")
@Getter
@Setter
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private BigDecimal salary;

    @Enumerated
    private Position position;

    @ManyToOne
    private Picture picture;

    @ManyToOne
    private Team team;

}
