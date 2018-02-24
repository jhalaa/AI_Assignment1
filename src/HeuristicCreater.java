import data.GraphEdges;
import data.GraphNode;

import java.util.List;

public class HeuristicCreater {
    public static int HeuristicFunction(GraphNode node, GraphNode dest) {
        return 0;
    }

    public static int HeuristicFunction2(GraphNode node, GraphNode dest) throws IllegalArgumentException {
        LowestCost lowestCost = new LowestCost();
        List<GraphEdges> search = lowestCost.search(Initialiser.initializeGraph(), node, dest, false).get(0);
        int cost =0;
        for(GraphEdges edge:search){
            cost+=edge.getCost();
        }
        return cost-1;
    }

}
