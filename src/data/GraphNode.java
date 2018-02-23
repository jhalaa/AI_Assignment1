package data;

public class GraphNode {
    public String name;

    public GraphNode(String name){
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        GraphNode node = (GraphNode)obj;
        return this.name.equals(node.name);
    }

    @Override
    public int hashCode() {
        int result = 0;
        for(char c: name.toCharArray()){
            result+=7*c;
        }
        return result;
    }


}
