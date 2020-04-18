import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class BasicQueueOperation {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        String[] input = scanner.nextLine().split("\\s+");
        int numbersToPoll = Integer.parseInt(input[1]);
        int searchedNumber = Integer.parseInt(input[2]);

        Arrays.stream(scanner.nextLine().split("\\s+")).forEach(e -> queue.offer(Integer.parseInt(e)));

        for (int i = 0; i < numbersToPoll; i++) {
            queue.poll();
        }

        if (queue.contains(searchedNumber)){
            System.out.println(queue.contains(searchedNumber));
        }else {
            System.out.println(queue.stream().min(Integer::compareTo).orElse(0));
        }
    }
}
