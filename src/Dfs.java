
import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

public class Dfs implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest) {

        //List of all paths from src to goal node.
        List<List<GraphEdges>> result = new ArrayList<>();

        Stack<List<GraphEdges>> frontier = new Stack<>();
        frontier.push( (List)
                graph.getEdges()
                .stream()
                .filter(edge -> edge.getFrom().equals(src))
                .collect(Collectors.toList()));


        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.pop();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            Set<GraphNode> visited = new HashSet<>();
            visited.addAll(getValuesFrom(curr));
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode) && visited.add(edge.getTo())) {
                    List<GraphEdges> temp = curr;
                    temp.add(edge);
                    if (edge.getTo().equals(dest))
                        result.add(temp);
                    else
                        frontier.push(temp);
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
