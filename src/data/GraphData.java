package data;

public class GraphData{
    private String from;
    private String to;
    private int cost;

    public GraphData(String from, String to, int cost){
        this.from = from;
        this.to=to;
        this.cost=cost;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }
}