package src.functionalProgramming;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class CustomComparator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Comparator<Integer> comparator =
                Comparator.comparingInt((Integer integer) -> Math.abs(integer % 2)).thenComparingInt(integer -> integer);

        Arrays.stream(scanner.nextLine().split("\\s+"))
                .map(Integer::parseInt)
                .sorted(comparator)
                .map(e -> e + " ")
                .forEach(System.out::print);
    }
}
