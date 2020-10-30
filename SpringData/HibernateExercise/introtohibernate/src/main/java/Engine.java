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
        //ChangeCasingEx02();

        //Ex03
        //ContainsEmployeeEx03();

        //Ex04
        //EmployeesWithSalaryEx04();

        //Ex05
        //EmployeesFromDepartmentEx05();
    }

    private void EmployeesFromDepartmentEx05() {
        entityManager.createQuery("select e as full_name from Employee e where e.department.id = 6 order by e.salary , e.id",Employee.class)
                .getResultList()
                .forEach(e -> System.out.printf("%s %s from Research and Development - $%.2f%n"
                        ,e.getFirstName(),e.getLastName(),e.getSalary()));
    }

    private void EmployeesWithSalaryEx04() {
        entityManager.createQuery("select e from Employee e where e.salary > 50000",Employee.class)
                .getResultList()
                .forEach(e -> System.out.println(e.getFirstName()));
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
