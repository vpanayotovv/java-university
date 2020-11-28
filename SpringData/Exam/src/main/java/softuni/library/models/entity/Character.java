package softuni.library.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "characters")
@Getter
@Setter
@NoArgsConstructor
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private int age;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private LocalDate birthday;

    @ManyToOne
    private Book book;
}
