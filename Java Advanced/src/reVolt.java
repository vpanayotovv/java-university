import java.util.Scanner;

public class reVolt {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int row = Integer.parseInt(scanner.nextLine());
        int col = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[row][col];
        for (int r = 0; r < matrix.length; r++) {
            matrix[r] = scanner.nextLine().toCharArray();
        }
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row]);
            }
            System.out.println();
        }

    }
}
