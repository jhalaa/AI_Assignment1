import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

public class IterativeDeepening implements SearchStrategizer {
    static int MAX_BOUND = 20;
    static int BOUND = 9;
    static int depth = 1;

    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {
        List<List<GraphEdges>> result = new ArrayList<>();

        while (depth < BOUND) {
            DLS(graph, src, result, dest, searchMode);
            depth++;
        }
        if (depth == BOUND && result.isEmpty() && BOUND<MAX_BOUND) {
            System.out.println("Bound is reached and no path is found! Repeat the search by raising the Bound.");
            BOUND++;
            return search(graph, src, dest, searchMode);
        }

        // if result not found
        if (result.isEmpty())
            throw new IllegalArgumentException("No path Exists!");

        //if only first solution
        if(!searchMode)
            result.subList(1,result.size()).clear();
        return result;
    }

    private void DLS(Graph graph, GraphNode src, List<List<GraphEdges>> result, GraphNode dest, boolean searchMode) throws IllegalArgumentException {
        Stack<List<GraphEdges>> frontier = new Stack<>();

        //if source and destination are the same
        if (src.equals(dest))
            throw new IllegalArgumentException("Source and destination are the same");


        // adding source and destination to frontier
        List initialNodes = graph.getEdges()
                .stream()
                .filter(edge -> edge.getFrom().equals(src))
                .map(edge -> {
                    List temp = new ArrayList();
                    temp.add(edge);
                    return temp;
                })
                .collect(Collectors.toList());
        frontier.addAll(initialNodes);

        // if source or destination not present in graph
        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dest)) {
            throw new IllegalArgumentException("Start or goal node is not in the graph!");
        }

        int d = depth;
        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.pop();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            Set<GraphNode> visited = new HashSet<>();
            visited.addAll(MyHelper.getValuesFrom(curr));
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode) && visited.add(edge.getTo()) && d > 0) {
                    List<GraphEdges> temp = new ArrayList<>(curr);
                    temp.add(edge);
                    if (edge.getTo().equals(dest)) {
                        result.add(temp);
                    }
                    frontier.push(temp);
                    d--;
                }
            }
        }
    }
}