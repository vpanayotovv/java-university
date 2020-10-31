import entities.Address;
import entities.Employee;
import entities.Project;
import entities.Town;

import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
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

        //Ex06
        //AddingANewAddressEx06();

        //Ex07
        //AddressesWithEmployeeCountEx07();

        //Ex08
        //GetEmployeeWithProjectEx08();

        //Ex09
        //FindLatestTenProjectsEx09();

        //Ex10
        //IncreaseSalariesEx10();


    }

    private void IncreaseSalariesEx10() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("update Employee e set e.salary = e.salary * 1.12 where e.department.id in (1,2,4,11)").executeUpdate();

        entityManager.getTransaction().commit();

        List<Employee> resultList = entityManager.createQuery("select e from Employee e where e.department.id in (1,2,4,11)", Employee.class)
                .getResultList();
        for (Employee employee : resultList) {
            System.out.printf("%s %s ($%.2f)%n",employee.getFirstName(),employee.getLastName(),employee.getSalary());
        }

    }

    private void FindLatestTenProjectsEx09() {

        String pattern = "yyyy-MM-dd HH:mm:ss.s";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);


        entityManager.createQuery("select p from Project p order by p.startDate desc", Project.class)
                .setMaxResults(10)
                .getResultList().stream().sorted(Comparator.comparing(Project::getName)).forEach(project -> {
            System.out.printf("Project name: %s\n" +
                    " \tProject Description: %s ...\n" +
                    " \tProject Start Date:%s\n" +
                    " \tProject End Date: %s\n", project.getName(), project.getDescription().substring(0, 35), project.getStartDate().format(formatter), project.getEndDate());
        });


    }

    private void GetEmployeeWithProjectEx08() {
        System.out.println("Enter employee id:");
        int id = Integer.parseInt(scanner.nextLine());
        Employee employee = entityManager.createQuery("select e from Employee e where e.id = :id", Employee.class)
                .setParameter("id", id)
                .getSingleResult();
        System.out.printf("%s %s - %s", employee.getFirstName(), employee.getLastName(), employee.getJobTitle());
        System.out.println();
        employee.getProjects().stream().sorted(Comparator.comparing(Project::getName)).forEach(e -> System.out.println(e.getName()));
    }

    private void AddressesWithEmployeeCountEx07() {
        List<Address> addresses = entityManager.createQuery("select a from Address a order by a.employees.size desc ", Address.class)
                .setMaxResults(10).getResultList();
        for (Address address : addresses) {
            System.out.printf("%s, %s - %d employees%n", address.getText(), address.getTown().getName(), address.getEmployees().size());
        }
    }

    private void AddingANewAddressEx06() {
        Address address = createAddress("Vitoshka 15");
        System.out.println("Enter employees last name:");
        String lastName = scanner.nextLine();
        Employee name = entityManager.createQuery("select e from Employee e where e.lastName = :name", Employee.class)
                .setParameter("name", lastName).getSingleResult();
        entityManager.getTransaction().begin();
        name.setAddress(address);
        entityManager.getTransaction().commit();
    }

    private Address createAddress(String text) {
        Address address = new Address();
        entityManager.getTransaction().begin();
        address.setText(text);
        entityManager.getTransaction().commit();
        entityManager.persist(address);
        return address;
    }

    private void EmployeesFromDepartmentEx05() {
        entityManager.createQuery("select e as full_name from Employee e where e.department.id = 6 order by e.salary , e.id", Employee.class)
                .getResultList()
                .forEach(e -> System.out.printf("%s %s from Research and Development - $%.2f%n"
                        , e.getFirstName(), e.getLastName(), e.getSalary()));
    }

    private void EmployeesWithSalaryEx04() {
        entityManager.createQuery("select e from Employee e where e.salary > 50000", Employee.class)
                .getResultList()
                .forEach(e -> System.out.println(e.getFirstName()));
    }

    private void ContainsEmployeeEx03() {
        System.out.println("Enter employee full_name:");
        String fullName = scanner.nextLine();

        List<Employee> list = entityManager
                .createQuery("select e from Employee e where concat(e.firstName,' ', e.lastName) = :name", Employee.class)
                .setParameter("name", fullName)
                .getResultList();

        if (list.isEmpty()) {
            System.out.println("No");
        } else {
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
