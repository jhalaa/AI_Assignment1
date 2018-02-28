
import java.util.*;

public class Initialiser {
    public static Graph initializeGraph() {
        Set<GraphEdges> edges = new HashSet<>();
        Set<GraphNode> nodes = new HashSet<>();

        //initialise nodes of graph
        GraphNode mail = new GraphNode("mail");
        GraphNode ts = new GraphNode("ts");
        GraphNode o103 = new GraphNode("o103");
        GraphNode o109 = new GraphNode("o109");
        GraphNode o119 = new GraphNode("o119");
        GraphNode b1 = new GraphNode("b1");
        GraphNode b2 = new GraphNode("b2");
        GraphNode b3 = new GraphNode("b3");
        GraphNode b4 = new GraphNode("b4");
        GraphNode c1 = new GraphNode("c1");
        GraphNode c2 = new GraphNode("c2");
        GraphNode c3 = new GraphNode("c3");
        GraphNode d1 = new GraphNode("d1");
        GraphNode d2 = new GraphNode("d2");
        GraphNode d3 = new GraphNode("d3");
        GraphNode o125 = new GraphNode("o125");
        GraphNode o123 = new GraphNode("o123");
        GraphNode r123 = new GraphNode("r123");
        GraphNode o111 = new GraphNode("o111");
        GraphNode storage = new GraphNode("storage");

        nodes.addAll(Arrays.asList(mail,ts,o103,o109,o111,o119,o123,o125,storage,r123,b1,b2,b3,b4,c1,c2,c3,d1,d2,d3));

        //initialise edges
        GraphEdges e1 = new GraphEdges(ts,mail,6);
        GraphEdges e2 = new GraphEdges(mail,ts,6);
        GraphEdges e3 = new GraphEdges(o103,ts,8);
        GraphEdges e4 = new GraphEdges(ts,o103,8);
        GraphEdges e5 = new GraphEdges(o103,b3,4);
        GraphEdges e6 = new GraphEdges(o103,o109,12);
        GraphEdges e7 = new GraphEdges(o109,o103,12);
        GraphEdges e8 = new GraphEdges(o109,o119,16);
        GraphEdges e9 = new GraphEdges(o119,o109,16);
        GraphEdges e10 = new GraphEdges(o109,o111,4);
        GraphEdges e11 = new GraphEdges(o111,o109,4);
        GraphEdges e12 = new GraphEdges(b1,c2,3);
        GraphEdges e13 = new GraphEdges(b1,b2,6);
        GraphEdges e14 = new GraphEdges(b2,b1,6);
        GraphEdges e15 = new GraphEdges(b2,b4,3);
        GraphEdges e16 = new GraphEdges(b4,b2,3);
        GraphEdges e17 = new GraphEdges(b3,b1,4);
        GraphEdges e18 = new GraphEdges(b1,b3,4);
        GraphEdges e19 = new GraphEdges(b3,b4,7);
        GraphEdges e20 = new GraphEdges(b4,b3,7);
        GraphEdges e21 = new GraphEdges(b4,o109,7);
        GraphEdges e22 = new GraphEdges(c1,c3,8);
        GraphEdges e23 = new GraphEdges(c3,c1,8);
        GraphEdges e24 = new GraphEdges(c2,c3,6);
        GraphEdges e25 = new GraphEdges(c3,c2,6);
        GraphEdges e26= new GraphEdges(c2,c1,4);
        GraphEdges e27 = new GraphEdges(c1,c2,4);
        GraphEdges e28 = new GraphEdges(d2,d3,4);
        GraphEdges e29 = new GraphEdges(d3,d2,4);
        GraphEdges e30 = new GraphEdges(d1,d3,8);
        GraphEdges e31 = new GraphEdges(d3,d1,8);
        GraphEdges e32 = new GraphEdges(o125,d2,2);
        GraphEdges e33 = new GraphEdges(d2,o125,2);
        GraphEdges e34 = new GraphEdges(o123,o125,4);
        GraphEdges e35 = new GraphEdges(o125,o123,4);
        GraphEdges e36 = new GraphEdges(o123,r123,4);
        GraphEdges e37 = new GraphEdges(r123,o123,4);
        GraphEdges e38 = new GraphEdges(o119,o123,9);
        GraphEdges e39 = new GraphEdges(o123,o119,9);
        GraphEdges e40 = new GraphEdges(o119,storage,7);
        GraphEdges e41 = new GraphEdges(storage,o119,7);

        edges.addAll(Arrays.asList(e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16,e17,e18,e19,e20,e21,e22,e23,e24,e25,e26,e27,e28,e29,e30,e31,e32,e33,e34,e35,e36,e37,e38,e39,e40,e41));

        Graph graph=new Graph(nodes,edges);
        return graph;
    }
}