import entities.Employee;
import entities.Town;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Scanner;

public class Engine implements Runnable {

    private final EntityManager entityManager;
    private final Scanner scanner;

    Engine(EntityManager entityManager) {
        this.entityManager = entityManager;
        scanner = new Scanner(System.in);
    }

    public void run() {
        //Ex02
        ChangeCasingEx02();

        //Ex3
        ContainsEmployeeEx03();

    }

    private void ContainsEmployeeEx03() {
        System.out.println("Enter employee full_name:");
        String fullName = scanner.nextLine();

        List<Employee> list = entityManager
                .createQuery("select e from Employee e where concat(e.firstName,' ', e.lastName) = :name" ,Employee.class)
                .setParameter("name",fullName)
                .getResultList();

        if (list.isEmpty()){
            System.out.println("No");
        }else {
            System.out.println("Yes");
        }

    }

    private void ChangeCasingEx02() {
        List<Town> listOfTowns = entityManager.createQuery("select t from Town t where length(t.name) <= 5 ", Town.class).getResultList();

        entityManager.getTransaction().begin();
        listOfTowns.forEach(entityManager::detach);
        for (Town town : listOfTowns) {
            town.setName(town.getName().toLowerCase());
        }
        listOfTowns.forEach(entityManager::merge);
        entityManager.flush();
        entityManager.getTransaction().commit();

    }
}
