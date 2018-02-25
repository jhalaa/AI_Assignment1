import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.System.exit;

public class InformalSearchUsingHeuristic implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {
        List<List<GraphEdges>> result = new ArrayList<>();

        //if source and destination are the same
        if(src.equals(dest))
            throw new IllegalArgumentException("Source and destination are the same");

        Comparator<List<GraphEdges>> comparator = new Comparator<List<GraphEdges>>() {
            @Override
            public int compare(List<GraphEdges> o1, List<GraphEdges> o2) {
                int result=0;
                try {
                    result = Integer.compare(getH(o1, dest),getH(o2, dest));
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    exit(0);
                }
                return result;
            }
        };

        // adding source and destination to frontier
        Queue<List<GraphEdges>> frontier = new PriorityQueue<List<GraphEdges>>(comparator);
        List initialNodes = graph.getEdges()
                .stream()
                .filter(edge -> edge.getFrom().equals(src))
                .map(edge -> {List temp =new ArrayList(); temp.add(edge); return temp;})
                .collect(Collectors.toList());
        frontier.addAll(initialNodes);

        // if source or destination not present in graph
        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dest)) {
            throw new IllegalArgumentException("Start or goal node is not in the graph!");
        }

        result = MyHelper.getResult(graph, result, dest, frontier);

        // if result not found
        if(result.isEmpty())
            throw new IllegalArgumentException("No path Exists!");
        return result;
    }

    private int getH(List<GraphEdges> list, GraphNode dest) throws IllegalArgumentException {
        return HeuristicCreater.HeuristicFunction2(list.get(list.size() - 1).getTo(), dest);
    }
}