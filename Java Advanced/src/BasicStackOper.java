import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Scanner;

public class BasicStackOper {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numbersToPush = scanner.nextInt();
        int numberToPop = scanner.nextInt();
        int lookUpNumber = scanner.nextInt();

        scanner.nextLine();

        ArrayDeque<Integer> numbers = new ArrayDeque<>();

        String[] input = scanner.nextLine().split("\\s+");

        for (int i = 0; i < numbersToPush; i++) {
            numbers.push(scanner.nextInt());
        }
        for (int i = 0; i < numberToPop; i++) {
            numbers.pop();
        }

        if (numbers.contains(lookUpNumber)){
            System.out.println("true");
        }else {
            if(numbers.isEmpty()){
                System.out.println(Collections.min(numbers));
            }
        }
    }

}
