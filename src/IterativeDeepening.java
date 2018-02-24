import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

public class IterativeDeepening implements SearchStrategizer {
    static int BOUND = 3;
    static int depth = 1;

    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {
        List<List<GraphEdges>> result = new ArrayList<>();

        while (depth < BOUND) {
            DLS(graph, src, result, dest, searchMode);
            if (!result.isEmpty()) {
                return result;
            }
            depth++;
        }
        if (depth == BOUND && result.isEmpty()) {
            System.out.println("Bound is reached and no path is found! Repeat the search by raising the Bound.");
            BOUND++;
            return search(graph, src, dest, searchMode);
        }
        if (result.isEmpty())
            throw new IllegalArgumentException("No path Exists!");
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
            visited.addAll(getValuesFrom(curr));
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode) && visited.add(edge.getTo()) && d > 0) {
                    List<GraphEdges> temp = new ArrayList<>(curr);
                    temp.add(edge);
                    if (edge.getTo().equals(dest)) {
                        result.add(temp);
                        if (!searchMode) {
                            return;
                        }
                    }
                    frontier.push(temp);
                    d--;
                }
            }
        }
    }

    private List<GraphNode> getValuesFrom(List<GraphEdges> curr) {
        List<GraphNode> result = new ArrayList<>();
        Iterator iterator = curr.iterator();
        while (iterator.hasNext()) {
            GraphEdges edge = (GraphEdges) iterator.next();
            result.add(edge.getTo());
            result.add(edge.getFrom());
        }
        return result;
    }
}