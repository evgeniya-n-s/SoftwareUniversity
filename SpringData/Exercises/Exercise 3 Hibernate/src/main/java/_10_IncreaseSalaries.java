import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class _10_IncreaseSalaries {
    private static final String SELECT_EMPLOYEE_DEPARTMENT_NAME = "SELECT e FROM Employee e WHERE e.department.name IN ( :departments )";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        List<String> departmentNames = Arrays.asList(
                "Engineering",
                "Tool Design",
                "Marketing",
                "Information Services");

        entityManager.
                createQuery(SELECT_EMPLOYEE_DEPARTMENT_NAME, Employee.class).
                setParameter("departments", departmentNames).
                getResultList().
                forEach(e -> {
                    e.setSalary(e.getSalary().multiply(BigDecimal.valueOf(1.12)));
                    entityManager.persist(e);
                });

        entityManager.
                createQuery(SELECT_EMPLOYEE_DEPARTMENT_NAME, Employee.class).
                setParameter("departments", departmentNames).
                getResultList().
                forEach(e -> {
                    String format = String.format("%s %s ($%.2f)", e.getFirstName(), e.getLastName(), e.getSalary());
                    System.out.println(format);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
