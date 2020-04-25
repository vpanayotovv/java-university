package src.ma3x;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class Position {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayDeque<String> deque = new ArrayDeque<>();
        int[][] matrix = readMatrix(scanner);
        int number = Integer.parseInt(scanner.nextLine());
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] == number){
                    deque.add(row + " " +col);
                }
            }
        }
        if (deque.isEmpty()){
            System.out.println("not found");
        }else {
            for (String string : deque) {
                System.out.println(string);
            }
        }


    }
    private static int[][] readMatrix(Scanner scanner) {
        String[] input = scanner.nextLine().split("\\s+");
        int row = Integer.parseInt(input[0]);
        int col = Integer.parseInt(input[1]);
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
