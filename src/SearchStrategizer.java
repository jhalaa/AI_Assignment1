import data.Graph;
import data.GraphNode;

import java.util.List;

public interface SearchStrategizer {
    public List<List<GraphNode>> search(Graph graph, GraphNode src, GraphNode dest);
}
