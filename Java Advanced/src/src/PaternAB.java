package src;

import java.util.Scanner;

public class PaternAB {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] input = scanner.nextLine().split(",\\s+");
        int matrixSize = Integer.parseInt(input[0]);
        String pattern = input[1];
        int[][] matrix = new int[matrixSize][matrixSize];
        int counter = 1;
        if (pattern.equals("A")) {
            for (int col = 0; col < matrixSize; col++) {
                for (int row = 0; row < matrixSize; row++) {
                    matrix[row][col] = counter++;
                }
            }

        } else {
            for (int col = 0; col < matrixSize; col++) {
                if (col % 2 == 0) {
                    for (int row = 0; row < matrixSize; row++) {
                        matrix[row][col] = counter++;
                    }
                } else {
                    for (int row = matrix.length - 1; row >= 0; row--) {
                        matrix[row][col] = counter++;
                    }
                }
            }
        }

        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        for (int[] aMatrix : matrix) {
            for (int anAMatrix : aMatrix) {
                System.out.print(anAMatrix + " ");
            }
            System.out.println();
        }
    }
}
