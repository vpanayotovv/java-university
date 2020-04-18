import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class BasickStackOperation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        String[] input = scanner.nextLine().split("\\s+");
        int numbersToPop = Integer.parseInt(input[1]);
        int searchedNumber = Integer.parseInt(input[2]);

        Arrays.stream(scanner.nextLine().split("\\s+")).forEach(e -> stack.push(Integer.parseInt(e)));
        for (int i = 0; i < numbersToPop; i++) {
            stack.pop();
        }

        if (stack.contains(searchedNumber)) {
            System.out.println(stack.contains(searchedNumber));
        } else {
            if (!stack.isEmpty()) {
                System.out.println(Collections.min(stack));
            }else {
                System.out.println("0");
            }
        }
    }
}
