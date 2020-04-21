package src;

import java.sql.SQLOutput;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class SetOfElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        LinkedHashSet<Integer> firstSet = readSet(scanner,n);
        LinkedHashSet<Integer> secondSet = readSet(scanner,m);
        firstSet.retainAll(secondSet);
        firstSet.forEach(e -> System.out.print(e + " "));

    }

    private static LinkedHashSet<Integer> readSet(Scanner scanner, int n) {
        LinkedHashSet<Integer> set = new LinkedHashSet<>();
        for (int i = 0; i < n; i++) {
            set.add(scanner.nextInt());
        }
        return set;
    }
}
