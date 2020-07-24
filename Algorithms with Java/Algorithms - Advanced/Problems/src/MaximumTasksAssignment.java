import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Scanner;

public class MaximumTasksAssignment {

    private static boolean[][] graph;
    private static int[] parents;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int people = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int tasks = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int nodes = people + tasks + 2;
        graph = new boolean[nodes][nodes];
        parents = new int[nodes];

        Arrays.fill(parents, -1);

        for (int i = 0; i < people; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < tasks; j++) {
                char letter = line.charAt(j);
                graph[i][people + j] = letter == 'Y';
            }
        }

        for (int i = 0; i < people; i++) {
            graph[nodes - 2][i] = true;
        }
        for (int i = 0; i < tasks; i++) {
            graph[people + i][nodes - 1] = true;
        }

        while (bfs(nodes - 2, nodes - 1)) {
            int node = nodes - 1;
            while (node != nodes - 2) {
                graph[parents[node]][node] = false;
                graph[node][parents[node]] = true;
                node = parents[node];
            }
        }

        for (int row = 0; row < people; row++) {
            for (int col = 0; col < tasks; col++) {
                if (graph[people + col][row]) {
                    System.out.printf("%s-%d%n", (char) (row + 65), col + 1);
                }
            }
        }
    }

    private static boolean bfs(int source, int dest) {
        boolean[] visited = new boolean[graph.length];

        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(source);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child = 0; child < graph[node].length; child++) {
                if (graph[node][child] && !visited[child]) {
                    visited[child] = true;
                    parents[child] = node;
                    queue.offer(child);
                }
            }
        }

        return visited[dest];

    }
}
