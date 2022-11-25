import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _05_EmployeesFromDepartment {
    private static final String DEPARTMENT_NAME = "Research and Development";
    private static final String SELECT_DEPARTMENT_NAME = "SELECT e FROM Employee e WHERE e.department.name = :departmentName ORDER BY e.salary ASC, e.id ASC";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(SELECT_DEPARTMENT_NAME, Employee.class)
                .setParameter("departmentName", DEPARTMENT_NAME)
                .getResultStream().forEach(e -> {
                    String format = String.format("%s %s from %s - $%.2f"
                            , e.getFirstName(), e.getLastName(), e.getDepartment().getName(), e.getSalary());
                    System.out.println(format);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
