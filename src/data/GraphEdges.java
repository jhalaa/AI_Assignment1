package data;

public class GraphEdges {
    private GraphNode from;
    private GraphNode to;
    private int cost;

    public GraphEdges(GraphNode from, GraphNode to, int cost){
        this.from = from;
        this.to=to;
        this.cost=cost;
    }

    public GraphNode getFrom() {
        return from;
    }

    public GraphNode getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }
}