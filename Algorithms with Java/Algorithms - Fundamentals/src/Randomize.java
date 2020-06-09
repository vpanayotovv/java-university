import java.util.concurrent.ThreadLocalRandom;

public class Randomize {
    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
        int[] random = getAsRand(arr);

        printArr(random);
    }

    private static void printArr(int[] random) {
        for (int aRandom : random) {
            System.out.print(aRandom + " ");
        }
    }

    private static int[] getAsRand(int[] arr) {
        int[] result = new int[arr.length];

        ThreadLocalRandom random = ThreadLocalRandom.current();

        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[random.nextInt(arr.length - 1)];
        }

        return result;
    }
}
