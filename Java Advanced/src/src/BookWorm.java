package src;

import java.util.Scanner;

public class BookWorm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder(scanner.nextLine());
        int n = Integer.parseInt(scanner.nextLine());
        int rowOfPlayer = 0;
        int colOfPlayer = 0;
        char[][] field = new char[n][n];
        for (int row = 0; row < field.length; row++) {
            String line = scanner.nextLine();
            field[row] = line.toCharArray();
            if (line.contains("P")) {
                rowOfPlayer = row;
                colOfPlayer = line.indexOf("P");
            }
        }
        String inputComand = scanner.nextLine();
        while (!inputComand.equals("end")) {
            int currentRow = 0;
            int currentCol = 0;
            switch (inputComand){
                case "up":
                    rowOfPlayer--;
                    if(field[rowOfPlayer][colOfPlayer] != '-'){
                        sb.append(field[rowOfPlayer][colOfPlayer]);
                        field[rowOfPlayer][colOfPlayer] = 'P';
                    }
                    field[rowOfPlayer][colOfPlayer] = '-';

                    break;
                case "down":

                    break;
                case "left":

                    break;

                default:

                    break;
            }
            inputComand = scanner.nextLine();
        }
        System.out.println(sb);


        printMatrix(field);
    }

    private static void printMatrix(char[][] field) {
        for (int row = 0; row < field.length; row++) {
            for (int col = 0; col < field[row].length; col++) {
                System.out.print(field[row][col]);
            }
            System.out.println();
        }
    }
}
