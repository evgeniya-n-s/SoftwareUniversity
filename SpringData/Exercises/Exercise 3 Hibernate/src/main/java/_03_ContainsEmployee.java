import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _03_ContainsEmployee {
    private static final String SELECT_EMPLOYEE_FIRST_NAME_LAST_NAME = "SELECT COUNT(e) FROM Employee e " +
            "WHERE e.firstName = :first_name AND e.lastName = : last_name";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Scanner scanner = new Scanner(System.in);
        String[] inputName = scanner.nextLine().split(" ");
        String firstName = inputName[0];
        String lastName = inputName[1];

        Long countOfMatches = entityManager.createQuery(SELECT_EMPLOYEE_FIRST_NAME_LAST_NAME, Long.class)
                .setParameter("first_name", firstName)
                .setParameter("last_name", lastName)
                .getSingleResult();
        if (countOfMatches > 0) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
