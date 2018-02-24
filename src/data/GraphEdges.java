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

    @Override
    public boolean equals(Object obj) {
        GraphEdges edge = (GraphEdges) obj;
        return this.getFrom().equals(edge.getFrom()) && this.getTo().equals(edge.getTo()) && this.getCost()==edge.getCost();
    }

    @Override
    public int hashCode() {
        return this.getTo().hashCode() + this.getFrom().hashCode();
    }
}