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
import java.util.Stack;

import data.Graph;
import data.GraphEdges;
import data.GraphNode;


public class AStar implements SearchStrategizer {


    @Override
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {
        List<List<GraphEdges>> result = new ArrayList<>();
        Stack<List<GraphEdges>> frontier = new Stack<>();

        Comparator<List<GraphEdges>> comparator = new Comparator<List<GraphEdges>>() {
            @Override
            public int compare(List<GraphEdges> o1, List<GraphEdges> o2) {
                return Integer.compare(getF(o2, dest),getF(o1, dest));
            }
        };
        Queue<List<GraphEdges>> pq = new PriorityQueue<List<GraphEdges>>(comparator);

        for (GraphEdges edge : graph.getEdges()) {
            if (edge.getFrom().equals(src)) {
                pq.add(new ArrayList<GraphEdges>(Arrays.asList(edge)));
            }
        }

        if (pq.isEmpty()) {
            throw new IllegalArgumentException("Start node is not in the graph!");
        }
        while (!pq.isEmpty()) {
            frontier.push(pq.poll());
        }
        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.pop();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            Set<GraphNode> visited = new HashSet<>();
            visited.addAll(getValuesFrom(curr));
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode) && visited.add(edge.getTo())) {
                    List<GraphEdges> temp = new ArrayList<>(curr);
                    temp.add(edge);
                    if (edge.getTo().equals(dest)) {
                        result.add(temp);
                        return result;
                    }
                    else
                        pq.add(temp);
                }
            }
            while (!pq.isEmpty()) {
                frontier.push(pq.poll());
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

    private int getF(List<GraphEdges> list, GraphNode dest) {
        if (list.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        int totalCost = 0;
        for (int i = 0; i < list.size(); i++) {
            totalCost += list.get(i).getCost();
        }
        return totalCost + HeuristicCreater.HeuristicFunction(list.get(list.size() - 1).getTo(), dest);
    }
}
