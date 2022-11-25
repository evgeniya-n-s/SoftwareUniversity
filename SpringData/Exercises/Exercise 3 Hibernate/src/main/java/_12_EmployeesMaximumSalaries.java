import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class _12_EmployeesMaximumSalaries {
    private static final String SELECT_MAX_SALARY_DEPARTMENT = "SELECT e.department.name, MAX(e.salary) FROM Employee e GROUP BY e.department.name HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(SELECT_MAX_SALARY_DEPARTMENT, Object[].class)
                .getResultList()
                .forEach(e -> {
                    String format = String.format("%s %.2f", e[0], e[1]);
                    System.out.println(format);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
