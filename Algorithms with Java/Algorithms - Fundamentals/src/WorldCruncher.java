import java.util.Scanner;

public class WorldCruncher {
    private static String[] words;
    private static String[] permutes;
    private static String targetText;
    private static boolean[] used;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        words = scanner.nextLine().split(",\\s+");
        permutes = new String[words.length];
        used = new boolean[words.length];
        targetText = scanner.nextLine();

        permute(0);

    }

    private static void permute(int index) {
        if (index == permutes.length) {
            print();
        }
        for (int i = 0; i < permutes.length; i++) {
            if (!used[i]) {
                used[i] = true;
                permutes[index] = words[i];
                permute(index + 1);
                used[i] = false;
            }
        }
    }

    private static void print() {
        String join = String.join("", permutes);
        if (join.contains(targetText)){
            for (int i = 0; i < permutes.length; i++) {
                System.out.print(permutes[i] + " ");
            }
        }
    }
}
