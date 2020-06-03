import java.util.Scanner;

public class ReverseArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] input = scanner.nextLine().split("\\s+");

        reverseArray(input, input.length - 1);

    }

    private static void reverseArray(String[] input, int index) {
        if (index < 0) {
            return;
        }
        System.out.print(input[index] + " ");

        reverseArray(input, index - 1);
    }
}
