package app.domain.entities;


import javax.persistence.*;

@Entity
@Table(name = "teachers")
public class Teacher {

    private long id;
    private String firstName;
    private String lastName;
    private String degree;

    public Teacher(String firstName, String lastName, String degree) {
        setFirstName(firstName);
        setLastName(lastName);
        setDegree(degree);
    }

    public Teacher() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name ="fist_name")
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name ="last_name")
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    public String getDegree() {
        return this.degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
}
