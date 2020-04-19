package functionalProgramming;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Function;

public class CustomMinFunction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Function<int[], Integer> function = arr ->{
            int min = Integer.MAX_VALUE;
            for (int number : arr) {
                if (min > number) {
                    min = number;
                }
            }

            return min;
        };
        System.out.println(function.apply(Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray()));

    }
}
