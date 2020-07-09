import java.util.*;

public class Dijkstra {

    public static List<Integer> dijkstraAlgorithm(int[][] graph, int sourceNode, int destinationNode) {

        int[] distances = new int[graph.length];
        int[] prev = new int[graph.length];

        Arrays.fill(prev,-1);

        boolean[] visited = new boolean[graph.length];
        Arrays.fill(distances,Integer.MAX_VALUE);
        distances[sourceNode] = 0;

        PriorityQueue<Integer> queue = new PriorityQueue<>(Comparator.comparingInt(node -> distances[node]));

        queue.offer(sourceNode);

        while (!queue.isEmpty()){
            int parent = queue.poll();
            visited[parent] = true;
            int[] child = graph[parent];

            for (int i = 0; i < child.length; i++) {
                if (child[i] != 0 && !visited[i]){
                    queue.offer(i);
                    int newDistance = distances[parent] + graph[parent][i];

                    if (newDistance < distances[i]){

                        distances[i] = newDistance;
                        prev[i] = parent;
                    }
                }
            }

        }
        List<Integer> path = new ArrayList<>();
        path.add(destinationNode);
        int n = prev[destinationNode];
        while (n != -1){
            path.add(n);
            n = prev[n];
        }
        Collections.reverse(path);

        return path.size() == 1 ? null : path;
    }
}
