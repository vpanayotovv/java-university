import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String nextLine = scanner.nextLine();
            if (nextLine.trim().equals("")) {
                graph.add(new ArrayList<>());
            } else {
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
            if (!visited[i]) {
                result.add(new ArrayDeque<>());
                dfs(i, result, graph, visited);
            }
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
        Map<String, Integer> dependenciesCount = getDependenciesCount(graph);
        List<String> sorted = new ArrayList<>();

        while (!graph.isEmpty()) {
            String keyWithoutDependencies = graph.keySet()
                    .stream()
                    .filter(key -> dependenciesCount.get(key) == 0)
                    .findFirst()
                    .orElse(null);
            if (keyWithoutDependencies == null) {
                break;
            }

            for (String child : graph.get(keyWithoutDependencies)) {
                dependenciesCount.put(child, dependenciesCount.get(child) - 1);
            }

            sorted.add(keyWithoutDependencies);
            graph.remove(keyWithoutDependencies);

        }
        if (!graph.isEmpty()){
            throw new IllegalArgumentException();
        }

        return sorted;
    }

    private static Map<String, Integer> getDependenciesCount(Map<String, List<String>> graph) {
        Map<String, Integer> dependenciesCount = new LinkedHashMap<>();
        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            dependenciesCount.putIfAbsent(node.getKey(), 0);
            for (String child : node.getValue()) {
                dependenciesCount.putIfAbsent(child, 0);
                dependenciesCount.put(child, dependenciesCount.get(child) + 1);
            }
        }
        return dependenciesCount;
    }
}
