import java.util.Scanner;

public class HelensAbduction {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int energyOfParis = Integer.parseInt(scanner.nextLine());
        int rowOfMatrix = Integer.parseInt(scanner.nextLine());
        char[][] matrix = new char[rowOfMatrix][];

        int parisRow = 0;
        int parisCol = 0;
        int helenaRow = 0;
        int helenaCol = 0;
        for (int i = 0; i < rowOfMatrix; i++) {
            String line = scanner.nextLine();
            matrix[i] = line.toCharArray();
            if (line.contains("P")) {
                parisRow = i;
                parisCol = line.indexOf("P");
            }
            if (line.contains("H")) {
                helenaRow = i;
                helenaCol = line.indexOf("H");
            }
        }
        boolean isAbducted = false;
        while (energyOfParis > 0 && !isAbducted) {
            energyOfParis--;
            String[] input = scanner.nextLine().split("\\s+");
            String direction = input[0];
            int row = Integer.parseInt(input[1]);
            int col = Integer.parseInt(input[2]);
            matrix[row][col] = 'S';
            if (direction.equals("up") && canMove(parisRow - 1, parisCol, matrix)) {
                matrix[parisRow][parisCol] = '-';
                parisRow--;
                if (matrix[parisRow][parisCol] == 'S') {
                    energyOfParis -= 2;
                } else if (matrix[parisRow][parisCol] == 'H') {
                    isAbducted = true;
                }
                matrix[parisRow][parisCol] = 'P';
            } else if (direction.equals("down") && canMove(parisRow + 1, parisCol, matrix)) {
                matrix[parisRow][parisCol] = '-';
                parisRow++;
                if (matrix[parisRow][parisCol] == 'S') {
                    energyOfParis -= 2;
                } else if (matrix[parisRow][parisCol] == 'H') {
                    isAbducted = true;
                }
            } else if (direction.equals("left") && canMove(parisRow, parisCol - 1, matrix)) {
                matrix[parisRow][parisCol] = '-';
                parisCol--;
                if (matrix[parisRow][parisCol] == 'S') {
                    energyOfParis -= 2;
                } else if (matrix[parisRow][parisCol] == 'H') {
                    isAbducted = true;
                }
            } else if (direction.equals("right") && canMove(parisRow, parisCol + 1, matrix)) {
                matrix[parisRow][parisCol] = '-';
                parisCol++;
                if (matrix[parisRow][parisCol] == 'S') {
                    energyOfParis -= 2;
                } else if (matrix[parisRow][parisCol] == 'H') {
                    isAbducted = true;
                }
            }
        }
        if (isAbducted) {
            matrix[parisRow][parisCol] = '-';
            System.out.println("Paris has successfully abducted Helen! Energy left: " + energyOfParis);
        } else {
            matrix[parisRow][parisCol] = 'X';
            System.out.println("Paris died at " + parisRow + ";" + parisCol + ".");
        }
        printMatrix(matrix);
    }

    private static boolean canMove(int currentRow, int currentCol, char[][] matrix) {
        return currentRow >= 0 && currentRow < matrix.length && currentCol >= 0 && currentCol < matrix[currentRow].length;
    }

    private static void printMatrix(char[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }
            System.out.println();
        }
    }
}
