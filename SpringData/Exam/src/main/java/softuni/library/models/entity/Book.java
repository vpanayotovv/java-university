package softuni.library.models.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "books")
@Getter
@Setter
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column
    private int edition;

    @Override
    public String toString() {
        return this.name;
    }

    @Column(nullable = false)
    private LocalDate written;

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    private Author author;

    @OneToMany(mappedBy = "book")
    private Set<Character> characters;

    @ManyToMany(mappedBy = "books")
    private Set<Library> libraries;

}
