import java.util.Scanner;

public class VariationsWithRepetition {
    private static String[] elements;
    private static String[] variations;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        variations = new String[Integer.parseInt(scanner.nextLine())];

        variations(0);

    }

    private static void variations(int index) {
        if (index == variations.length){
            print(variations);
            return;
        }
        for (int i = 0; i < elements.length; i++) {
                variations[index] = elements[i];
                variations(index + 1);
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ",arr));
    }
}
