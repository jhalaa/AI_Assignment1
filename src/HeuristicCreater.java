import data.GraphEdges;
import data.GraphNode;

import java.util.List;

class HeuristicCreater {
    static int HeuristicFunction(GraphNode node, GraphNode dest) {
        return 0;
    }

    static int HeuristicFunction2(GraphNode node, GraphNode dest) throws IllegalArgumentException {
        LowestCost lowestCost = new LowestCost();
        List<GraphEdges> search = lowestCost.search(Initialiser.initializeGraph(), node, dest, false).get(0);
        int cost = search.stream().mapToInt(GraphEdges::getCost).sum();
        return cost-1;
    }

}
