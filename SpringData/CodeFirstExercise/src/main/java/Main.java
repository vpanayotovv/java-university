import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {

    private static final String WIZARD_DEPOSITS_PU = "wizard_deposits";
    private static final String SALES_PU = "sales";

    public static void main(String[] args) {

        EntityManagerFactory entityManagerFactory = Persistence
                .createEntityManagerFactory(SALES_PU);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Engine engine = new Engine(entityManager);

        engine.run();
    }
}
