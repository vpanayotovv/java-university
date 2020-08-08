package Exam;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Problem01 {
    private static int[][] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int nodes = Integer.parseInt(reader.readLine());
        int edges = Integer.parseInt(reader.readLine());

        graph = new int[nodes][nodes];

        int[] points = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        int start = points[0];
        int destination = points[1];

        for (int i = 0; i < edges; i++) {
            int[] input = Arrays.stream(reader.readLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();

            graph[input[0]][input[1]] = input[2];
            graph[input[1]][input[0]] = input[2];
        }
        boolean[] visited = new boolean[graph.length];
        int[] prev = new int[graph.length];
        Arrays.fill(prev,-1);

        int[] distances = new int[graph.length];
        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[start] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparing(node -> distances[node]));

        queue.offer(start);

        while (!queue.isEmpty()){
            int parent = queue.poll();
            visited[parent] = true;

            int[] child = graph[parent];

            for (int i = 0; i < child.length; i++) {
                if (child[i] != 0 && !visited[i]){
                    queue.offer(i);
                    int newDistance = distances[parent] +graph[parent][i];
                    if (newDistance < distances[i]){
                        distances[i] = newDistance;
                        prev[i] = parent;
                    }
                }
            }

        }

        List<Integer> path = new ArrayList<>();
        path.add(destination);

        int n = prev[destination];

        while (n != -1){
            path.add(n);
            n = prev[n];
        }

        Collections.reverse(path);

        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        System.out.println(distances[destination]);

    }
}
