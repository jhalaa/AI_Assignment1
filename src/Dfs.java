
import data.GraphData;

import java.util.List;
import java.util.Stack;

public class Dfs implements SearchStrategizer {
    public void search(List<GraphData> graph) {

        //Source node -> hard coded right nw. Make it argument later
        String srcNode = "";

        //Source node -> hard coded right nw. Make it argument later
        String destNode = "";

        Stack<String> frontier = new Stack<>();
        frontier.add(srcNode);

        while(!frontier.isEmpty()){
            String curr = frontier.pop();
            for(GraphData node: graph){
                if(node.getFrom().equals(curr)){
                    if(node.getTo().equals(destNode))
                        return ;
                    else
                        frontier.push(node.getTo());
                }
            }
        }
    }
}
