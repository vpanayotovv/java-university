import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.stream.Collectors;

public class TowersOfHanoi {
    private static Deque<Integer> source = new ArrayDeque<>();
    private static Deque<Integer> spare = new ArrayDeque<>();
    private static Deque<Integer> destination = new ArrayDeque<>();
    private static int steps = 1;
    private static StringBuilder builder = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        for (int i = n; i >= 1; i--) {
            source.push(i);
        }
        printRods();
        solve(n, source, destination, spare);
        System.out.println(builder.toString());
    }

    private static void solve(int n, Deque<Integer> source, Deque<Integer> destination, Deque<Integer> spare) {
        if (n == 1) {
            destination.push(source.pop());
            builder.append("Step #").append(steps++).append(": Moved disk").append(System.lineSeparator());
            printRods();
        } else {
            solve(n - 1, source, spare, destination);
            solve(1, source, destination, spare);
            solve(n - 1, spare, destination, source);
        }
    }

    private static void printRods() {
        builder.append(String.format("Source: %s%nDestination: %s%nSpare: %s%n", formatRod(source), formatRod(destination), formatRod(spare)))
                .append(System.lineSeparator());
    }

    private static String formatRod(Deque<Integer> stack) {
        return stack.stream().sorted(Comparator.reverseOrder()).map(String::valueOf).collect(Collectors.joining(", "));
    }
}
