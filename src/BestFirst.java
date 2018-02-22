import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class BestFirst implements SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {
        List<List<GraphEdges>> result = new ArrayList<>();

        Comparator<List<GraphEdges>> comparator = new Comparator<List<GraphEdges>>() {
            @Override
            public int compare(List<GraphEdges> o1, List<GraphEdges> o2) {
                return Integer.compare(getH(o1, dest),getH(o2, dest));
            }
        };
        Queue<List<GraphEdges>> frontier = new PriorityQueue<List<GraphEdges>>(comparator);

        for (GraphEdges edge : graph.getEdges()) {
            if (edge.getFrom().equals(src)) {
                frontier.add(new ArrayList<GraphEdges>(Arrays.asList(edge)));
            }
        }
        if (frontier.isEmpty()) {
            throw new IllegalArgumentException("Start node is not in the graph!");
        }

        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.poll();
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

    private int getH(List<GraphEdges> list, GraphNode dest) {
        return HeuristicCreater.HeuristicFunction(list.get(list.size() - 1).getTo(), dest);
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