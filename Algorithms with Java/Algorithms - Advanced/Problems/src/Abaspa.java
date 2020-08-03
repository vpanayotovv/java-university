import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Abaspa {
    private static int[][] dp;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String first = scanner.nextLine();
        String second = scanner.nextLine();

        dp = new int[first.length()][second.length()];

        int bestLength = -1;
        int bestRow = -1;
        int bestCol = -1;

        for (int row = 0; row < first.length(); row++) {
            for (int col = 0; col < second.length(); col++) {
                if (first.charAt(row) == second.charAt(col)) {
                    dp[row][col] = getBestPrev(row, col) + 1;
                }
                if (dp[row][col] > bestLength) {
                    bestLength = dp[row][col];
                    bestRow = row;
                    bestCol = col;
                }
            }
        }
        List<Character> result = new ArrayList<>();
        while (bestRow >= 0 && bestCol >= 0 && dp[bestRow][bestCol] != 0) {
            result.add(first.charAt(bestRow));
            bestRow--;
            bestCol--;

        }
        Collections.reverse(result);
        for (Character symbol : result) {
            System.out.print(symbol);
        }
    }

    private static int getBestPrev(int row, int col) {
        if (row - 1 < 0 || col - 1 < 0) {
            return 0;
        }

        return dp[row - 1][col - 1];
    }
}
