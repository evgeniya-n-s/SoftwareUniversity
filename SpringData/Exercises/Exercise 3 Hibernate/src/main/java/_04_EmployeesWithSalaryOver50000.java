import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _04_EmployeesWithSalaryOver50000 {
    private static final String SELECT_EMPLOYEES_SALARY_OVER_50000 = "SELECT e.firstName FROM Employee e WHERE e.salary > 50000";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(SELECT_EMPLOYEES_SALARY_OVER_50000, String.class).getResultList().forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
