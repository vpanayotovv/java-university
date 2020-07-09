import java.util.*;

public class KruskalAlgorithm {

    public static List<Edge> kruskal(int numberOfVertices, List<Edge> edges) {

        PriorityQueue<Edge> orderedEdges = new PriorityQueue<>(edges);
        List<Edge> forest = new ArrayList<>();

        int[] parents = new int[numberOfVertices];

        while (!orderedEdges.isEmpty()) {

            Edge edge = orderedEdges.poll();

            int source = edge.getStartNode();
            int destination = edge.getEndNode();

            int firstParent = findRoot(source, parents);
            int secondParent = findRoot(destination, parents);

        }

        return forest;
    }

    public static int findRoot(int node, int[] parents) {



        return 0;
    }
}
