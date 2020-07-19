import java.util.*;
import java.util.stream.Collectors;

public class CableNetwork {

    private static Map<Integer, List<Edge>> graph = new LinkedHashMap<>();

    private static int spend = 0;

    private static class Edge implements Comparable<Edge> {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
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

        int budget = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int nodes = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);
        int edgesCount = Integer.parseInt(scanner.nextLine().split("\\s+")[1]);

        boolean[] used = new boolean[nodes];

        for (int i = 0; i < edgesCount; i++) {
            String[] input = scanner.nextLine().split("\\s+");

            int from = Integer.parseInt(input[0]);
            int to = Integer.parseInt(input[1]);
            int weight = Integer.parseInt(input[2]);

            Edge edge = new Edge(from, to, weight);

            graph.putIfAbsent(from, new ArrayList<>());
            graph.get(from).add(edge);

            if (input.length == 4) {
                used[from] = used[to] = true;
            }
        }

        prim(used, budget);

        System.out.println("Budget used: " + spend);

    }

    private static boolean prim(boolean[] used, int budget) {
        PriorityQueue<Edge> edges = graph.values().stream().flatMap(List::stream)
                .collect(Collectors.toCollection(PriorityQueue::new));

        while (!edges.isEmpty()) {
            Edge minEdge = edges.poll();
            int from = minEdge.from;
            int to = minEdge.to;
            int weight = minEdge.weight;

            int removedValue = -1;

            if (used[from] && !used[to]) {
                used[to] = true;
                removedValue = weight;
            }
            if (!used[from] && used[to]) {
                used[from] = true;
                removedValue = weight;
            }
            if (removedValue != -1 && budget - removedValue > 0) {
                budget -= removedValue;
                spend += removedValue;
            } else if (budget - removedValue < 0) {
                return false;
            }

        }

        return true;
    }
}
