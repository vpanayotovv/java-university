package Exam;

import java.util.Arrays;
import java.util.Scanner;

public class Problem03 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int[] energy = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] points = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int myEnergyPoints = Integer.parseInt(scanner.nextLine());

        int[][] dp = new int[points.length + 1][myEnergyPoints + 1];

        for (int i = 1; i <= points.length; i++) {
            for (int j = 1; j <= myEnergyPoints; j++) {
                if (energy[i - 1] <= j) {
                    dp[i][j] = Math.max(points[i - 1] + dp[i - 1][j - energy[i - 1]],
                            dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        System.out.println(dp[points.length][myEnergyPoints]);
    }
}
