import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        //Before you run check persistence file about root and password
        //the program will create one database with all tables
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("relation");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();


        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
