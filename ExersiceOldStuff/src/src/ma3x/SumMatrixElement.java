package src.ma3x;

import java.util.Arrays;
import java.util.Scanner;

public class SumMatrixElement {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] ints = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        int rows = ints[0];
        int cols = ints[1];

        int[][] matrix = new int[rows][cols];

        for (int row = 0; row < matrix.length; row++) {
            matrix[row] = Arrays.stream(scanner.nextLine().split(", ")).mapToInt(Integer::parseInt).toArray();
        }
        System.out.println(rows);
        System.out.println(cols);
        int sum = 0;
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
               sum += matrix[row][col];
            }
        }
        System.out.println(sum);
    }
}
