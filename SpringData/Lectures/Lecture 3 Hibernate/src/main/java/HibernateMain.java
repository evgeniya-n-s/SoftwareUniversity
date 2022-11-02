import entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class HibernateMain {
    public static void main(String[] args) {
        //System.out.println("works");
        Configuration configuration = new Configuration();
        configuration.configure();

        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        Student example = new Student();
        example.setName("Tosho");
        session.persist(example);

        List<Student> students = session.createQuery("FROM Student", Student.class).list();

        for (Student student : students) {
            System.out.println(student.getId() + " " + student.getName());
        }

        Student fromDB = session.get(Student.class, 2);
        System.out.println(fromDB.getId() + " " + fromDB.getName());

        session.getTransaction().commit();
        session.close();
    }
}
