import entities.Address;
import entities.Employee;
import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;
import java.util.Scanner;
import java.util.stream.Collectors;

public class _08_GetEmployeeWithProject {
    private static final String SELECT_EMPLOYEE_BY_ID = "FROM Employee e WHERE e.id =: id";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        int inputId = Integer.parseInt(scanner.nextLine());

        entityManager.createQuery(SELECT_EMPLOYEE_BY_ID, Employee.class)
                .setParameter("id", inputId)
                .getResultStream()
                .forEach(e -> {
                    String format = String.format("%s %s - %s%n%s", e.getFirstName(), e.getLastName(), e.getJobTitle(),
                            e.getProjects().stream().sorted(Comparator.comparing(Project::getName))
                                    .map(p -> p.getName()).collect(Collectors.joining(System.lineSeparator())));
                    System.out.println(format);
                });
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
