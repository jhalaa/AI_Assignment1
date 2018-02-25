import java.util.*;
import java.util.stream.Collectors;

import data.Graph;
import data.GraphEdges;
import data.GraphNode;


public class AStar implements SearchStrategizer {


    @Override
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {
        List<List<GraphEdges>> result = new ArrayList<>();

        //if source and destination are the same
        if(src.equals(dest))
            throw new IllegalArgumentException("Source and destination are the same");

        // if source or destination not present in graph
        if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dest)) {
            throw new IllegalArgumentException("Start or goal node is not in the graph!");
        }

        Comparator<List<GraphEdges>> comparator = new Comparator<List<GraphEdges>>() {
            @Override
            public int compare(List<GraphEdges> o1, List<GraphEdges> o2) {
                return Integer.compare(getF(o1, dest),getF(o2, dest));
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


        Set<GraphNode> visited = new HashSet<>();
        visited.add(src);
        if (MyHelper.getResult(graph, dest, result, frontier, visited))
            return result;

        // if result not found
        if(result.isEmpty())
            throw new IllegalArgumentException("No path Exists!");
        return result;
    }




    private int getF(List<GraphEdges> list, GraphNode dest) {
        if (list.isEmpty())
            return Integer.MAX_VALUE;
        int totalCost = list.stream().mapToInt(GraphEdges::getCost).sum();
        return totalCost + HeuristicCreater.HeuristicFunction(list.get(list.size() - 1).getTo(), dest);
    }
}
