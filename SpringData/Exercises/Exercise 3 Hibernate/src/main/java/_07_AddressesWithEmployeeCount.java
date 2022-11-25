import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class _07_AddressesWithEmployeeCount {
    private static final String COUNT_ADDRESSES_BY_EMPLOYEE = "FROM Address a ORDER BY a.employees.size DESC";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager
                .createQuery(COUNT_ADDRESSES_BY_EMPLOYEE,
                        Address.class)
                .setMaxResults(10)
                .getResultStream()
                .forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
