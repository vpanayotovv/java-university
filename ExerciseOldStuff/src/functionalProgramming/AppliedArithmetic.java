package functionalProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AppliedArithmetic {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> numbers = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        Function<List<Integer>, List<Integer>> functionAdd = list -> list.stream()
                .map(e -> e + 1)
                .collect(Collectors.toList());
        Function<List<Integer>, List<Integer>> functionMultiply = list -> list.stream()
                .map(e -> e * 2)
                .collect(Collectors.toList());
        Function<List<Integer>, List<Integer>> functionSubtract = list -> list.stream()
                .map(e -> e - 1)
                .collect(Collectors.toList());
        Function<List<Integer>, List<Integer>> functionPrint = list -> list.stream()
                .peek(e -> System.out.print(e + " "))
                .collect(Collectors.toList());
        String input = scanner.nextLine();

        while (!input.equals("end")) {
            String command = input;
            switch (command) {
                case "add":
                    numbers = functionAdd.apply(numbers);
                    break;
                case "multiply":
                    numbers = functionMultiply.apply(numbers);
                    break;
                case "subtract":
                    numbers = functionSubtract.apply(numbers);
                    break;
                default:
                    numbers = functionPrint.apply(numbers);
                    break;
            }

            input = scanner.nextLine();
        }
    }
}
