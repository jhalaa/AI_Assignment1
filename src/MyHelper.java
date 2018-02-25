import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

public class MyHelper {
    static List<GraphNode> getValuesFrom(List<GraphEdges> curr) {
        List<GraphNode> result = new ArrayList<>();
        List values = curr.stream()
                .map(edge -> { List temp = new ArrayList();
                    temp.add(edge.getTo());  temp.add(edge.getFrom()); return temp;})
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        result.addAll(values);
        return result;
    }

    public static List<List<GraphEdges>> getResult(Graph graph, List<List<GraphEdges>> result, GraphNode dest, Queue<List<GraphEdges>> frontier) {
        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.poll();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            Set<GraphNode> visited = new HashSet<>();
            visited.addAll(MyHelper.getValuesFrom(curr));

            graph.getEdges().stream()
                    .filter(edge -> edge.getFrom().equals(lastNode) && visited.add(edge.getTo()))
                    .map(edge -> {
                        List<GraphEdges> temp = new ArrayList<>(curr);
                        temp.add(edge);
                        return temp;
                    })
                    .forEach(list -> {
                        GraphEdges edge = list.get(list.size()-1);
                        if (edge.getTo().equals(dest)) {
                            result.add(list);
                        }
                        else
                            frontier.add(list);
                    });
        }
        return result;

    }

    public static List<List<GraphEdges>> getResult(Graph graph, List<List<GraphEdges>> result, GraphNode dest, Stack<List<GraphEdges>> frontier) {
        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.pop();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            Set<GraphNode> visited = new HashSet<>();
            visited.addAll(MyHelper.getValuesFrom(curr));

            graph.getEdges().stream()
                    .filter(edge -> edge.getFrom().equals(lastNode) && visited.add(edge.getTo()))
                    .map(edge -> {
                        List<GraphEdges> temp = new ArrayList<>(curr);
                        temp.add(edge);
                        return temp;
                    })
                    .forEach(list -> {
                        GraphEdges edge = list.get(list.size()-1);
                        if (edge.getTo().equals(dest)) {
                            result.add(list);
                        }
                        else
                            frontier.push(list);
                    });
        }
        return result;
    }

    public static boolean getResult(Graph graph, GraphNode dest, List<List<GraphEdges>> result, Queue<List<GraphEdges>> frontier, Set<GraphNode> visited) {
        while (!frontier.isEmpty()) {
            List<GraphEdges> curr = frontier.poll();
            GraphNode lastNode = curr.get(curr.size() - 1).getTo();
            for (GraphEdges edge : graph.getEdges()) {
                if (edge.getFrom().equals(lastNode) && !visited.contains(edge.getTo())) {
                    List<GraphEdges> temp = new ArrayList<>(curr);
                    temp.add(edge);
                    if (edge.getTo().equals(dest)) {
                        result.add(temp);
                        return true;
                    }
                    else
                        frontier.add(temp);
                }
            }
            visited.add(lastNode);
        }
        return false;
    }
}
