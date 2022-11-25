import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;

public class _09_FindLatest10Projects {
    private static final String SELECT_PROJECT_BY_START_DATE = "FROM Project p ORDER BY p.startDate DESC";
    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.createQuery(SELECT_PROJECT_BY_START_DATE, Project.class)
                .setMaxResults(10)
                .getResultList().stream()
                .sorted(Comparator.comparing(Project::getName))
                .forEach(p->{
                    String format = String.format("Project name: %s%n   Project Description: %s%n   Project Start Date:%s%n   Project End Date: %s"
                            ,p.getName()
                            ,p.getDescription()
                            ,p.getStartDate().minusHours(3).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"))
                            ,p.getEndDate());
                    System.out.println(format);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
