import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class _06_AddingNewAddressAndUpdatingEmployee {
    private static final String UPDATE_QUERY = "UPDATE Employee e SET e.address =: address WHERE e.lastName =: name";

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);

        String newAddress = "Vitoshka 15";
        Address address = new Address();
        address.setText(newAddress);
        entityManager.persist(address);

        String lastName = scanner.nextLine();

        int count = entityManager.createQuery(UPDATE_QUERY)
                .setParameter("name", lastName)
                .setParameter("address", address)
                .executeUpdate();

        if (count > 0) {
            entityManager.getTransaction().commit();
        } else {
            entityManager.getTransaction().rollback();
        }

        entityManager.close();
//        System.out.println(count);
    }
}
