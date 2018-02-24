import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

public class Dfs implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {

        List<List<GraphEdges>> result = new ArrayList<>();

        //if source and destination are the same
        if(src.equals(dest))
            throw new IllegalArgumentException("Source and destination are the same");

        // adding source and destination to frontier
        Stack<List<GraphEdges>> frontier = new Stack<>();
        List initialNodes = graph.getEdges()
                .stream()
                .filter(edge -> edge.getFrom().equals(src))
                .map(edge -> {List temp =new ArrayList(); temp.add(edge); return temp;})
                .collect(Collectors.toList());
        frontier.addAll(initialNodes);

        // if a result of edge length one exists add to frontier
        if(frontier.stream().anyMatch(list -> list.get(0).getTo().equals(dest))){
            GraphEdges c = frontier.stream()
                    .filter(list -> list.get(0).getTo().equals(dest))
                    .flatMap(Collection::stream)
                    .findFirst()
                    .orElse(null);
            frontier.remove(Arrays.asList(c));
            result.add(Arrays.asList(c));
        }

        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dest)) {
            throw new IllegalArgumentException("Start or goal node is not in the graph!");
        }

        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.pop();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            Set<GraphNode> visited = new HashSet<>();
            visited.addAll(MyHelper.getValuesFrom(curr));
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
                        frontier.push(temp);
                }

            }
        }

        // if result not found
        if(result.isEmpty())
            throw new IllegalArgumentException("No path Exists!");
        return result;
    }
}