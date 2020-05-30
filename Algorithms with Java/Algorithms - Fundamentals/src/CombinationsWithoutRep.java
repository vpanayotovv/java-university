import java.util.Scanner;

public class CombinationsWithoutRep {
    private static String[] elements;
    private static String[] variations;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        elements = scanner.nextLine().split("\\s+");
        variations = new String[Integer.parseInt(scanner.nextLine())];

        combinations(0, 0);

    }

    private static void combinations(int index, int start) {
        if (index == variations.length) {
            print(variations);
            return;
        }
        for (int i = start; i < elements.length; i++) {
            variations[index] = elements[i];
            combinations(index + 1, i + 1);
        }
    }

    private static void print(String[] arr) {
        System.out.println(String.join(" ", arr));
    }
}

// INPUT:
// A B C
// 2
//
// OUTPUT:
// A B
// A C
// B C