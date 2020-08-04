import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class LongestPath {

    private static int[][] graph;
    private static int[] distance;
    private static boolean[] visited;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine());
        int edges = Integer.parseInt(scanner.nextLine());

        graph = new int[nodes + 1][nodes + 1];

        for (int i = 0; i < edges; i++) {
            int[] input = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            int source = input[0];
            int dest = input[1];
            int weight = input[2];

            graph[source][dest] = weight;
        }

        int source = Integer.parseInt(scanner.nextLine());
        int destination = Integer.parseInt(scanner.nextLine());

        distance = new int[graph.length];

        Arrays.fill(distance,Integer.MIN_VALUE);

        distance[source] = 0;

        visited = new boolean[graph.length];

        ArrayDeque<Integer> sorted = new ArrayDeque<>();

        for (int i = 1; i < graph.length; i++) {
            dfs(i, sorted);
        }

        while (!sorted.isEmpty()){

            int node = sorted.pop();
            for (int i = 1; i < graph[node].length; i++) {
                if (graph[node][i] != 0){
                    if (distance[node] + graph[node][i] > distance[i]){
                        distance[i] = distance[node] + graph[node][i];
                    }
                }
            }
        }

        System.out.println(distance[destination]);

    }

    private static void dfs(int node, ArrayDeque<Integer> sorted) {
        if (visited[node]) {
            return;
        }
        visited[node] = true;
        for (int i = 1; i < graph[node].length; i++) {
            if (graph[node][i] != 0) {
                dfs(i, sorted);
            }
        }

        sorted.push(node);
    }
}
