
import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;

public class Dfs implements SearchStrategizer {
    public List<List<GraphNode>> search(Graph graph, GraphNode src, GraphNode dest) {

        //List of all paths from src to goal node.
        List<List<GraphNode>> result = new ArrayList<>();

        Stack<List<GraphNode>> frontier = new Stack<>();
        frontier.add(Arrays.asList(src));


        while (!frontier.isEmpty()) {
            List<GraphNode> curr = frontier.pop();
            GraphNode lastNode = curr.get(curr.size() - 1);
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode)) {
                    List<GraphNode> temp = curr;
                    temp.add(edge.getTo());
                    if (edge.getTo().equals(dest))
                        result.add(temp);
                    else
                        frontier.push(temp);
                }

            }
        }

        return result;
    }

}
