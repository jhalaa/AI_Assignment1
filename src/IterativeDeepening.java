import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

public class IterativeDeepening implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {
        List<List<GraphEdges>> result = new ArrayList<>();
        int depth = 0;
        //return the first path from src to dest
        while(true) {
            DLS(graph, src, depth, result, dest);
            if (!result.isEmpty()) {
                break;
            }
            depth++;
        }
        return result;
    }

    private void DLS(Graph graph, GraphNode src, int depth, List<List<GraphEdges>> result, GraphNode dest) {
        Stack<List<GraphEdges>> frontier = new Stack<>();

        for (GraphEdges edge : graph.getEdges()) {
            if (edge.getFrom().equals(src)) {
                frontier.push(new ArrayList<GraphEdges>(Arrays.asList(edge)));
            }
        }

        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.pop();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            System.out.println(curr.toString());
            Set<GraphNode> visited = new HashSet<>();
            visited.addAll(getValuesFrom(curr));
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode) && visited.add(edge.getTo()) && depth > 0) {
                    List<GraphEdges> temp = new ArrayList<>(curr);
                    temp.add(edge);
                    if (edge.getTo().equals(dest)) {
                        result.add(temp);
                        return;
                    }
                    frontier.push(temp);
                    depth--;
                }
            }
        }
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