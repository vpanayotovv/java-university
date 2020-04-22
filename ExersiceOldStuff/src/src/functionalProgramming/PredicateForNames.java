package src.functionalProgramming;

import java.util.Arrays;
import java.util.Scanner;

public class PredicateForNames {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        String[] strings = scanner.nextLine().split("\\s+");
        Arrays.stream(strings).filter(s -> s.length() <= n).forEach(System.out::println);
    }
}
