import data.GraphEdges;
import data.GraphNode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
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
}
