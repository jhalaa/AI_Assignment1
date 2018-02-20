import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;

public class Bfs implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest) throws IllegalArgumentException {
//List of all paths from src to goal node.
        List<List<GraphEdges>> result = new ArrayList<>();

        Queue<List<GraphEdges>> frontier = new LinkedList<>();
        frontier.addAll((Collection<? extends List<GraphEdges>>)
                graph.getEdges()
                        .stream()
                        .filter(edge -> edge.getFrom()
                                .equals(src)));
        if (frontier.isEmpty()) {
            throw new IllegalArgumentException("Start node is not in the graph!");
        }
        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.remove();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            Set<GraphNode> visited = new HashSet<>();
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode) && visited.add(edge.getTo())) {
                    List<GraphEdges> temp = curr;
                    temp.add(edge);
                    if (edge.getTo().equals(dest))
                        result.add(temp);
                    else
                        frontier.add(temp);
                }

            }
        }

        return result;
    }
}