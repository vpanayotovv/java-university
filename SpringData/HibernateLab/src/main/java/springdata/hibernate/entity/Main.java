package springdata.hibernate.entity;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        Configuration cfg = new Configuration();
        cfg.configure();

        SessionFactory sf = cfg.buildSessionFactory();

        Session session = sf.openSession();

        Student student = new Student("Hristo Georgiev");
        session.beginTransaction();

        session.save(student);

        session.getTransaction().commit();


        session.close();
    }
}
