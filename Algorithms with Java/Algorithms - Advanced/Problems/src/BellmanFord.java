import java.util.*;
import java.util.stream.Collectors;

public class BellmanFord {

    public static int[][] graph;
    public static int[] distances;
    public static int[] prev;


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


        bellmanFord(source);

        List<Integer> path = new ArrayList<>();
        path.add(destination);
        int node = prev[destination];

        while (node != -1) {
            path.add(node);
            node = prev[node];
        }
        Collections.reverse(path);

        System.out.println(path.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        System.out.println(distances[destination]);

    }

    private static void bellmanFord(int sourceNode) {
        distances = new int[graph.length];
        Arrays.fill(distances, Integer.MAX_VALUE);
        prev = new int[graph.length];
        Arrays.fill(prev, -1);

        distances[sourceNode] = 0;

        for (int i = 0; i < graph.length - 1; i++) {
            for (int row = 0; row < graph.length; row++) {
                for (int col = 0; col < graph[row].length; col++) {
                    if (graph[row][col] != 0) {
                        if (distances[row] != Integer.MAX_VALUE) {
                            int newValue = distances[row] + graph[row][col];
                            if (newValue < distances[col]) {
                                distances[col] = newValue;
                                prev[col] = row;
                            }
                        }
                    }
                }
            }
        }
        System.out.println();
    }
}
