package src.functionalProgramming;

import java.util.*;
import java.util.stream.Collectors;

public class ReverseAndExclude {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> collect = Arrays.stream(scanner.nextLine().split("\\s+"))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
        int n = Integer.parseInt(scanner.nextLine());
        Collections.reverse(collect);
        List<Integer> collectReversed = collect.stream().filter(integer -> integer % n != 0).collect(Collectors.toList());

        String join = String.join(" ", collectReversed.toString());
        System.out.println(join.replaceAll("\\[|]|,", ""));
    }
}
    