package softuni.library.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "libraries")
@Getter
@Setter
@NoArgsConstructor
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private String location;

    @Column
    private int rating;

    @ManyToMany
    private Set<Book> books;
}
