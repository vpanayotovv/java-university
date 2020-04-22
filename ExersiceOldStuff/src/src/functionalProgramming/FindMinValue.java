package src.functionalProgramming;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindMinValue {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Function<List<Integer>, Integer> function = integers -> {
          int min = Integer.MAX_VALUE;
          int index = -1;
            for (int i = 0; i < integers.size(); i++) {
                if (min >= integers.get(i)){
                    min = integers.get(i);
                    index = i;
                }
            }
            return index;
        };

        List<Integer> collect = Arrays.stream(scanner.nextLine().split("\\s+")).map(Integer::parseInt).collect(Collectors.toList());
        System.out.println(function.apply(collect));
    }
}
