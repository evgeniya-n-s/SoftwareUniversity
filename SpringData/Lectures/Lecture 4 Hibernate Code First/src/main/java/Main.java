import entitites.Bike;
import entitites.Car;
import entitites.Plane;
import entitites.Vehicle;
import hasEntities.Article;
import hasEntities.PlateNumber;
import hasEntities.Truck;
import hasEntities.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("relations");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Vehicle car = new Car("Ford Focus", "Petrol", 5);
        Vehicle bike = new Bike();
        Vehicle plane = new Plane("Airbus", "Petrol", 200);

        entityManager.persist(car);
       entityManager.persist(bike);
        entityManager.persist(plane);

       PlateNumber number = new PlateNumber("123");
        Truck truck1 = new Truck(number);
       Truck truck2 = new Truck(number);

       entityManager.persist(number);
       entityManager.persist(truck1);
       entityManager.persist(truck2);

        Car fromDB = entityManager.find(Car.class, 1L);

        System.out.println(fromDB.getSeats() + " " + fromDB.getModel());

        Article article = new Article("alabala");
        User author = new User("Toshko");

        author.addArtice(article);
        article.setAuthor(author);

        entityManager.persist(author);

        User user = entityManager.find(User.class, 2);

        System.out.println();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
