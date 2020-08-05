package examPrep;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class DataTransfer {
    private static int[][] graph;
    private static int[] parents;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        int[] points = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int source = points[0];
        int destination = points[1];

        graph = new int[nodes][nodes];
        parents = new int[graph.length];
        Arrays.fill(parents,-1);

        for (int i = 0; i < edges; i++) {
            int[] input = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            graph[input[0]][input[1]] = input[2];
        }
        int maxFlow = 0;
        while (bfs(source, destination)) {
            int flow = Integer.MAX_VALUE;
            int node = destination;
            while (node != source) {
                flow = Math.min(flow, graph[parents[node]][node]);
                node = parents[node];
            }
            maxFlow += flow;

            node = destination;

            while (node != source) {
                graph[parents[node]][node] -= flow;
                graph[node][parents[node]] += flow;
                node = parents[node];

            }
        }
        System.out.println(maxFlow);

    }

    private static boolean bfs(int source, int destination) {
        boolean[] visited = new boolean[graph.length];
        Arrays.fill(parents, -1);
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);
        visited[source] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int child = 0; child < graph.length; child++) {
                if (graph[node][child] > 0 && !visited[child]) {
                    visited[child] = true;
                    parents[child] = node;
                    queue.offer(child);
                }
            }
        }
        return visited[destination];

    }
}
