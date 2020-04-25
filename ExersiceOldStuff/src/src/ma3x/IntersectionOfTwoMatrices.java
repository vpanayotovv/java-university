package src.ma3x;

import java.util.Scanner;

public class IntersectionOfTwoMatrices {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        scanner.nextLine();
        String[][] matrix = new String[row][col];
        for (int r = 0; r < matrix.length; r++) {
            String[] split = scanner.nextLine().split("\\s+");
            matrix[r] = split;
        }
        String[][] matrixSecond = new String[row][col];
        for (int r = 0; r < matrixSecond.length; r++) {
            String[] split = scanner.nextLine().split("\\s+");
            matrixSecond[r] = split;
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals(matrixSecond[i][j])){
                    System.out.print(matrix[i][j] + " ");
                }else {
                    System.out.print("* ");
                }
            }
            System.out.println();
        }

    }
}
