package src.ma3x;

import java.util.Scanner;

public class Queen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        char[][] field = new char[8][8];
        for (int i = 0; i < field.length; i++) {
            field[i] = scanner.nextLine().replaceAll("\\s+", "").toCharArray();
        }


        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                if (field[row][col] == 'q' && isValidQueen(field, row, col)) {
                    System.out.println(row + " " + col);
                }
            }
        }
    }

    private static boolean isValidQueen(char[][] field, int row, int col) {
        for (int rowDirection = -1; rowDirection <= 1; rowDirection++) {
            for (int colDirection = -1; colDirection <= 1; colDirection++) {
                if (rowDirection == 0 && colDirection == 0){
                    continue;
                }

                int currentRow = row + rowDirection;
                int currentCol = col + colDirection;
                boolean validPosition = isValidPosition(field, currentRow, currentCol);

                while (validPosition) {
                    if ('q' == field[currentRow][currentCol]) {
                        return false;
                    }
                    currentRow = currentRow + rowDirection;
                    currentCol = currentCol + colDirection;

                    validPosition = isValidPosition(field, currentRow, currentCol);
                }
            }
        }

        return true;
    }

    private static boolean isValidPosition(char[][] field, int currentRow, int currentCol) {
        return currentRow >= 0
                && currentRow < field.length
                && currentCol >= 0
                && currentCol < field[currentRow].length;
    }
}
