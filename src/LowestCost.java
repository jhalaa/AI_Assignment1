
import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;

public class LowestCost implements SearchStrategizer {
    public List<List<GraphNode>> search(Graph graph, GraphNode src, GraphNode dest) {

        Set<String> visited = new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        Queue<List<String>> frontier = new PriorityQueue<>();

        frontier.add(Arrays.asList(src));

        while(!frontier.isEmpty()){
            List<String> curr = frontier.poll();
            String lastNode = curr.get(curr.size() - 1);
            if(visited.add(lastNode)){
                for(GraphEdges node: graph){
                    if(node.getFrom().equals(lastNode)){
                        curr.add(node.getTo());
                        if(node.getTo().equals(dest))
                            result.add(curr);
                        else
                            frontier.add(curr);
                    }
                }
            }
        }

        return result;

    }
}
