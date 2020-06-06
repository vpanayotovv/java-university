import java.util.Scanner;

public class CombinationsWithoutRep2 {
    private static int[] combinations;
    private static int n;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = Integer.parseInt(scanner.nextLine());
        int k = Integer.parseInt(scanner.nextLine());
        combinations = new int[k];

        combine(0, 1);

    }

    private static void combine(int index, int start) {
        if (index == combinations.length) {
            print(combinations);
            return;
        }

        for (int i = start; i <= n; i++) {
            combinations[index] = i;
            combine(index + 1, i + 1);
        }

    }

    private static void print(int[] combinations) {
        for (int i = 0; i < combinations.length; i++) {
            System.out.print(combinations[i] + " ");
        }
        System.out.println();
    }
}
