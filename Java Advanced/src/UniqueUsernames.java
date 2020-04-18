import java.util.LinkedHashSet;
import java.util.Scanner;

public class UniqueUsernames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        LinkedHashSet<String> userNames = new LinkedHashSet<>();

        for (int i = 0; i < n; i++) {
            String userName = scanner.nextLine();
            userNames.add(userName);
        }

        userNames.forEach(System.out::println);
    }
}

