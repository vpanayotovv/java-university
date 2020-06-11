import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <n; i++) {
            String nextLine = scanner.nextLine();
            if (nextLine.trim().equals("")){
                graph.add(new ArrayList<>());
            }else {
                List<Integer> nextNodes = Arrays.stream(nextLine.split("\\s+"))
                        .mapToInt(Integer::parseInt)
                        .boxed()
                        .collect(Collectors.toList());
                graph.add(nextNodes);
            }
        }
        List<Deque<Integer>> connectedComponents = getConnectedComponents(graph);

        for (Deque<Integer> connectedComponent : connectedComponents) {
            System.out.print("Connected component: ");
            for (Integer integer : connectedComponent) {
                System.out.print(integer + " ");
            }
            System.out.println();
        }

    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        List<Deque<Integer>> result = new ArrayList<>();

        for (int i = 0; i < graph.size(); i++) {
            result.add(new ArrayDeque<>());
            dfs(i,result,graph,visited);
        }
        return result;
    }

    private static void dfs(int node, List<Deque<Integer>> result, List<List<Integer>> graph, boolean[] visited) {
        if (!visited[node]) {
            visited[node] = true;
            for (int child : graph.get(node)) {
                dfs(child, result, graph, visited);
            }
            result.get(result.size() - 1).offer(node);
        }
    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        throw new AssertionError("Not Implemented");
    }
}
