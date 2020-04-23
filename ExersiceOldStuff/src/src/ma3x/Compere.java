package src.ma3x;
import java.util.Arrays;
import java.util.Scanner;

public class Compere {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[][] firstMa3x = readMatrix(scanner);
        int[][] secondMa3x = readMatrix(scanner);
        if (firstMa3x.length != secondMa3x.length ||
                firstMa3x[0].length != secondMa3x[0].length) {
            System.out.println("not equal");
            return;
        }
        boolean isEqual = true;
        for (int row = 0; row < firstMa3x.length; row++) {
            for (int col = 0; col < firstMa3x[row].length; col++) {
                if (firstMa3x[row][col] != secondMa3x[row][col]) {
                    isEqual = false;
                }
            }
        }
        if (isEqual) {
            System.out.println("equal");
        } else {
            System.out.println("not equal");
        }
    }

    private static int[][] readMatrix(Scanner scanner) {
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        scanner.nextLine();
        int[][] firstArr = new int[row][col];
        for (int r = 0; r < row; r++) {
            int[] split = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            firstArr[r] = split;
        }
        return firstArr;
    }
}
