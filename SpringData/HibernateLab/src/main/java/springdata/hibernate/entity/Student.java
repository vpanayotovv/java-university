package springdata.hibernate.entity;

import java.util.Date;

public class Student {
    private Long id;
    private String name;
    private Date regDate = new Date();

    public Student(String name) {
        this.name = name;
    }

    public Student(Long id, String name, Date regDate) {
        this.id = id;
        this.name = name;
        this.regDate = regDate;
    }

    public Student() {
    }

    public Long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public Date getRegDate() {
        return this.regDate;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", regDate=" + regDate +
                '}';
    }
}
