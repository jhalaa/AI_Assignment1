import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.List;

public interface SearchStrategizer {
    public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest);
}
