import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static final String WIZARD_DEPOSITS_PU = "wizard_deposits";
    private static final String SALES_PU = "sales";
    private static final String UNIVERSITY_PU = "university_system";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory(UNIVERSITY_PU);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Engine engine = new Engine(entityManager);

        engine.run();
    }
}
