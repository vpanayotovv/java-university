import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<Person> persons = new ArrayList<>();

        while (n-- > 0) {
            String name = scanner.next();
            int age = scanner.nextInt();

            persons.add(new Person(name,age));
        }
        persons
                .stream()
                .filter(p -> p.age > 30)
                .sorted(Comparator.comparing(person -> person.name))
                .forEach(System.out::println);
    }
}
