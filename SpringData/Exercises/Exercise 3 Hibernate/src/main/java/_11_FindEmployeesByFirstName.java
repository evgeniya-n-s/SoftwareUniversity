import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _11_FindEmployeesByFirstName {
    private static final String SELECT_EMPLOYEE_FIRST_NAME_PATTERN = "SELECT e FROM Employee e WHERE e.firstName LIKE :startWith";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String inputPattern = scanner.nextLine();

        entityManager.createQuery(SELECT_EMPLOYEE_FIRST_NAME_PATTERN, Employee.class)
                .setParameter("startWith", inputPattern + "%")
                .getResultList().forEach(e -> {
                    String format = String.format("%s %s - %s - ($%.2f)",
                            e.getFirstName(),
                            e.getLastName(),
                            e.getJobTitle(),
                            e.getSalary());
                    System.out.println(format);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
