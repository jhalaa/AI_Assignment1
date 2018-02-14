import data.GraphData;

import java.util.List;

public interface SearchStrategizer {
    public List<List<String>> search(List<GraphData> graph, String src, String dest);
}
