
import data.GraphData;

import java.util.*;

public class Dfs implements SearchStrategizer {
    public List<List<String>> search(List<GraphData> graph, String src, String dest) {
        Set<String> visited= new HashSet<>();
        List<List<String>> result = new ArrayList<>();

        Stack<List<String>> frontier = new Stack<>();
        frontier.add(Arrays.asList(src));

        while(!frontier.isEmpty()){
            List<String> curr = frontier.pop();
            String lastNode = curr.get(curr.size() - 1);
            if(visited.add(lastNode)){
                for(GraphData node: graph){
                    if(node.getFrom().equals(lastNode)){
                        curr.add(node.getTo());
                        if(node.getTo().equals(dest))
                            result.add(curr);
                        else
                            frontier.push(curr);
                    }
                }
            }
        }

        return result;
    }

}
