package Exam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Problem02 {
    private static StringBuilder builder = new StringBuilder();

    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int nodes = Integer.parseInt(reader.readLine());
        int edgesCount = Integer.parseInt(reader.readLine());
        graph = new int[nodes][nodes];

        for (int i = 0; i < edgesCount; i++) {
            String[] input = reader.readLine().split("\\s+");

            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            graph[from][to] = weight;
        }

        int cost = 0;

        int[] parents = new int[nodes];

        for (int i = 0; i < nodes; i++) {
            parents[i] = i;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(n -> graph[n[0]][n[1]]));

        for (int row = 0; row < graph.length; row++) {
            for (int col = 0; col < graph[row].length; col++) {
                if (graph[row][col] != 0) {
                    queue.offer(new int[]{row, col});
                }
            }
        }

        while (!queue.isEmpty()) {
            int[] minEdge = queue.poll();
            int from = minEdge[0];
            int to = minEdge[1];
            int weight = graph[from][to];

            int firstRoot = findRoot(from, parents);
            int secondRoot = findRoot(to, parents);

            if (firstRoot != secondRoot) {
                parents[secondRoot] = firstRoot;
                cost += weight;
                builder.append(String.format("%d %d%n", from, to));
            }
        }
        builder.append(cost);
        System.out.println(builder.toString().trim());

    }

    private static int findRoot(int node, int[] parents) {
        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }
}

