import java.util.*;
import java.util.stream.Collectors;

public class ModifiedKruskalAlgorithm {
    private static StringBuilder builder = new StringBuilder();

    private static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int weight;

        private Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);


        for (int i = 0; i < edgesCount; i++) {
            String[] input = scanner.nextLine().split("\\s+");

            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            Edge edge = new Edge(from, to, weight);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);
        }

        PriorityQueue<Edge> edges = graph.values()
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toCollection(PriorityQueue::new));

        int forestWeight = 0;

        int[] parents = new int[nodes];
        Arrays.fill(parents, -1);

        for (int i = 0; i < nodes; i++) {
            parents[i] = i;
        }
        while (!edges.isEmpty()) {
            Edge minEdge = edges.poll();
            int firstRoot = findRoot(minEdge.from, parents);
            int secondRoot = findRoot(minEdge.to, parents);

            if (firstRoot != secondRoot) {
                forestWeight += minEdge.weight;

                builder.append(String.format("(%d %d) -> %d%n", minEdge.from, minEdge.to, minEdge.weight));
                parents[secondRoot] = firstRoot;

            }
        }
        System.out.println(String.format("Minimum spanning forest weight: %d",forestWeight));
        System.out.println(builder.toString().trim());
    }

    private static int findRoot(int node, int[] parents) {
        while (parents[node] != node) {
            node = parents[node];
        }
        return node;
    }
}
