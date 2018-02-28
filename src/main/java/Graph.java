import java.util.Set;

public class Graph {

    private Set<GraphNode> nodes;
    private Set<GraphEdges> edges;

    public Graph(Set<GraphNode> nodes, Set<GraphEdges> edges){
        this.edges = edges;
        this.nodes=nodes;
    }

    public Set<GraphNode> getNodes() {
        return nodes;
    }


    public Set<GraphEdges> getEdges() {
        return edges;
    }

}
