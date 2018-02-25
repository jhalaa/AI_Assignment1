import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

public class Bfs implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {

        List<List<GraphEdges>> result = new ArrayList<>();

        // if source and destination are the same
        if(src.equals(dest))
            throw new IllegalArgumentException("Source and destination are the same");

        // adding source and destination to frontier
        Queue<List<GraphEdges>> frontier = new LinkedList<>();
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


        // if source or destination not present in graph
        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dest)) {
            throw new IllegalArgumentException("Start or goal node is not in the graph!");
        }

        result = MyHelper.getResult(graph, result, dest, frontier);

        // if result not found
        if(result.isEmpty())
            throw new IllegalArgumentException("No path Exists!");

        //if only first solution
        if(!searchMode)
            result.subList(1,result.size()).clear();
        return result;
    }


}