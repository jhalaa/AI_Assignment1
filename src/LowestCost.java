
import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;

public class LowestCost implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest) {
        List<List<GraphEdges>> result = new ArrayList<>();

        Comparator<List<GraphEdges>> comparator = new Comparator<List<GraphEdges>>() {
            @Override
            public int compare(List<GraphEdges> o1, List<GraphEdges> o2) {
                return Integer.compare(o1.get(o1.size()-1).getCost(),o2.get(o2.size()-1).getCost());
            }
        };
        Queue<List<GraphEdges>> frontier = new PriorityQueue<List<GraphEdges>>(comparator);

        frontier.addAll((Collection<? extends List<GraphEdges>>) graph.getEdges().stream().filter(edge -> edge.getFrom().equals(src)));

        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.poll();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode)) {
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
