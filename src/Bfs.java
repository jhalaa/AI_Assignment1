import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

public class Bfs implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {

        List<List<GraphEdges>> result = new ArrayList<>();

        Queue<List<GraphEdges>> frontier = new LinkedList<>();
        for (GraphEdges edge : graph.getEdges()) {
            if (edge.getFrom().equals(src)) {
                frontier.add(new ArrayList<GraphEdges>(Arrays.asList(edge)));
            }
        }

        if (frontier.isEmpty()) {
            throw new IllegalArgumentException("Start node is not in the graph!");
        }
        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.remove();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            Set<GraphNode> visited = new HashSet<>();
            visited.addAll(getValuesFrom(curr));
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode) && visited.add(edge.getTo())) {
                    List<GraphEdges> temp = new ArrayList<>(curr);
                    temp.add(edge);
                    if (edge.getTo().equals(dest)) {
                        result.add(temp);
                        if (!searchMode) {
                            return result;
                        }
                    }
                    else
                        frontier.add(temp);
                }

            }
        }
        return result;
    }

    private List<GraphNode> getValuesFrom(List<GraphEdges> curr) {
        List<GraphNode> result = new ArrayList<>();
        Iterator iterator = curr.iterator();
        while (iterator.hasNext()){
            GraphEdges edge = (GraphEdges) iterator.next();
            result.add(edge.getTo());
            result.add(edge.getFrom());
        }
        return result;
    }
}