package app.university_system.entities;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "teachers")
public class Teacher extends BasePerson {

    private double salaryPerHour;
    private Set<Course> courses;

    public Teacher() {
    }

    @Column(name = "salary_per_hour")
    public double getSalaryPerHour() {
        return this.salaryPerHour;
    }

    public void setSalaryPerHour(double salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }

    @OneToMany(mappedBy = "teacher",targetEntity = Course.class)
    public Set<Course> getCourses() {
        return this.courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
