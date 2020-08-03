import java.util.Arrays;
import java.util.Scanner;

public class TheMadGardener {

    public static class Sequence {
        int size;
        int prev;
        int sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] input = Arrays.stream(scanner.nextLine()
                .split("\\s+"))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] plants = new int[input.length + 1];

        for (int i = 1; i < plants.length; i++) {
            plants[i] = input[i - 1];
        }

        Sequence[] lis = getLIS(plants);

        int[] reversed = new int[plants.length];

        for (int i = 1; i < input.length; i++) {
            reversed[i] = plants[(plants.length - 1) - i + 1];

        }

        Sequence[] lisReverse = getLIS(reversed);

        int maxSize = 0;
        int maxSum = 0;
        int peek = 0;

        for (int i = 1; i < plants.length; i++) {
            int size = lis[i].size + lisReverse[(plants.length - 1) - i + 1].size;
            if (size >= maxSize) {
                maxSize = size;
                maxSum = lis[i].sum + lisReverse[(plants.length - 1) - i + 1].sum - plants[i];
                peek = i;
            }
        }

        int[] result = new int[plants.length];
        int element = lis[peek].size;
        int index = peek;
        int nextElement = 0;

        while (index != 0) {
            nextElement++;
            result[element--] = plants[index];
            index = lis[index].prev;
        }

        index = lisReverse[plants.length - peek].prev;

        while (index != 0) {
            result[++nextElement] = reversed[index];
            index = lisReverse[index].prev;
        }

        for (int i = 1; i < maxSize; i++) {

            System.out.print(result[i] + " ");

        }
        System.out.println();
        System.out.printf("%.2f%n", maxSum / ((maxSize - 1) * 1.00));
        System.out.println(maxSize - 1);


    }

    private static Sequence[] getLIS(int[] plants) {
        Sequence[] LIS = new Sequence[plants.length];
        LIS[0] = new Sequence();
        for (int i = 1; i <= plants.length - 1; i++) {
            LIS[i] = new Sequence();
            for (int j = 0; j < i; j++) {
                if (plants[j] <= plants[i]) {
                    if (LIS[j].size + 1 > LIS[i].size
                            || (LIS[j].size == LIS[i].size
                            && LIS[j].sum + plants[i] > LIS[i].sum)) {
                        LIS[i].sum = LIS[j].sum + plants[i];
                        LIS[i].size = LIS[i].size + 1;
                        LIS[i].prev = j;
                    }
                }
            }
        }

        return LIS;
    }
}
