package app.domain.entities;

import javax.persistence.*;

@Entity
@Table(name ="students")
public class Student {
    private long id;
    private String firstName;
    private String lastName;
    private int grade;

    public Student(String firstName,String lastName,int grade) {
        setFirstName(firstName);
        setLastName(lastName);
        setGrade(grade);
    }

    public Student() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
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
    public int getGrade() {
        return this.grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }
}
