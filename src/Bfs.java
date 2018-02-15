import data.Graph;
import data.GraphNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs implements SearchStrategizer {
    public List<List<GraphNode>> search(Graph graph, GraphNode src, GraphNode dest) {

        //Source node -> hard coded right nw. Make it argument later
        String srcNode = "";

        //Source node -> hard coded right nw. Make it argument later
        String destNode = "";

        Queue<String> frontier = new LinkedList<>();
        frontier.add(srcNode);

        while (!frontier.isEmpty()){
            String node = frontier.remove();

        }
    }
}
