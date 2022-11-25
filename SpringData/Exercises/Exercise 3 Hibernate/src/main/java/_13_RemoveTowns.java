import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class _13_RemoveTowns {
    private static final String SELECT_TOWN = "SELECT t FROM Town t WHERE t.name =: town";
    private static final String SELECT_ADDRESS_TOWN = "SELECT a FROM Address a WHERE a.town.name = :town";

    //not finish
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        Town town = entityManager.createQuery(SELECT_TOWN, Town.class)
                .setParameter("town", input)
                .getSingleResult();

        List<Address> addresses = entityManager.
                createQuery(SELECT_ADDRESS_TOWN, Address.class).
                setParameter("town", input).
                getResultList();

        int countAddress = addresses.size();

        if (countAddress == 0) {
            System.out.printf("There isn't a town %s",input);
            entityManager.close();
            return;
        }

        addresses.forEach(a -> {
            a.getEmployees().forEach(e -> e.setAddress(null));
            entityManager.remove(a);
        });

        entityManager.remove(town);
        if(countAddress ==1){
            System.out.printf("%d address in %s deleted", countAddress, input);
        }else {
            System.out.printf("%d addresses in %s deleted", countAddress, input);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
