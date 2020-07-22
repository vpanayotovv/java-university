import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArticulationPoints {

    private static List<Integer> points;
    private static boolean[] visited;
    private static List<Integer>[] graph;
    private static int[] parents;
    private static int[] dephts;
    private static int[] lowpoints;

    public static List<Integer> findArticulationPoints(List<Integer>[] targetGraph) {

        graph = targetGraph;
        points = new ArrayList<>();
        visited = new boolean[graph.length];
        parents = new int[graph.length];
        dephts = new int[graph.length];
        lowpoints = new int[graph.length];

        Arrays.fill(parents, -1);

        dfs(0, 1);


        return points;
    }

    private static void dfs(int node, int depth) {
        visited[node] = true;
        dephts[node] = depth;
        lowpoints[node] = depth;
        int childCount = 0;
        boolean isArticulation = false;

        for (int child : graph[node]) {
            if (!visited[child]) {
                parents[child] = node;
                childCount++;
                dfs(child, depth + 1);
                if (lowpoints[child] >= dephts[node]) {
                    isArticulation = true;
                }
                lowpoints[node] = Math.min(lowpoints[node], lowpoints[child]);
            } else if (parents[node] != child) {
                lowpoints[node] = Math.min(lowpoints[node], dephts[child]);
            }
        }
        if ((parents[node] == -1 && childCount > 1) || parents[node] != -1 && isArticulation) {
            points.add(node);
        }

    }
}
