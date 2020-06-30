import java.util.*;

public class Distance {
    private static int[][] graph;
    private static Map<Integer,Integer> indexMapper = new HashMap<>();
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int nodes = Integer.parseInt(scanner.nextLine());
        int pairs = Integer.parseInt(scanner.nextLine());

        graph = new int[nodes + 1][];

        for (int i = 1; i < nodes +1; i++) {
            String[] edges = scanner.nextLine().split(":");

            indexMapper.put(Integer.parseInt(edges[0]), i);
            if (edges.length == 1){
                graph[i] = new int[0];
            }else {
                graph[i] = Arrays.stream(edges[1]
                        .split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }
        while (pairs-- > 0){
            int[] dependencies = Arrays.stream(scanner.nextLine().split("-")).mapToInt(Integer::parseInt).toArray();

            System.out.printf("{%d, %d} -> ",dependencies[0],dependencies[1]);

            int[] prev = new int[graph.length];

            Arrays.fill(prev, -1);

            bfs(graph,indexMapper.get(dependencies[0]),indexMapper.get(dependencies[1]),prev);

            List<Integer> path = new ArrayList<>();

            int parent = prev[indexMapper.get(dependencies[1])];
            while (parent != -1){
                path.add(parent);
                parent = prev[parent];
            }

            int size = path.isEmpty() ? -1 : path.size();

            System.out.println(size);
        }
    }

    private static void bfs(int[][] graph, int source, int destination, int[] path) {
        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(source);

        boolean[] visited = new boolean[graph.length +1];
        visited[source] = true;

        while (!queue.isEmpty()){
            Integer node = queue.poll();
            if (node == destination){
                return;
            }
            for (int i = 0; i < graph[node].length; i++) {
                int child = indexMapper.get(graph[node][i]);
                if (!visited[child]){
                    path[child] = node;
                    visited[child] = true;
                    queue.offer(child);
                }
            }
        }
        path[source] = -1;
    }
}

// INPUT :
// 9
// 8
// 11:4
// 4:12 1
// 1:12 21 7
// 7:21
// 12:4 19
// :1 21
// 21:14 31
// 14:14
// 31:
// 11-7
// 11-21
// 21-4
// 19-14
// 1-4
// 1-11
// 31-21
// 11-14
