import java.util.Scanner;

public class PermutationsWithoutRepetitions2 {
    private static String[] elements;
    private static String[] permutes;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        permutes = new String[elements.length];
        permute(0);
    }

    private static void permute(int index) {
        if (index == elements.length) {
            print(elements);
            return;
        }
        permute(index + 1);
        for (int i = index + 1; i < elements.length; i++) {
            swap(elements, index,i);
            permute(index + 1);
            swap(elements, index,i);
        }
    }

    private static void swap(String[] arr, int first, int second) {
        String temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}
