import data.GraphData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Initialiser {
    public static List<GraphData> initializeGraph() {
        List<GraphData> graph = new ArrayList<>();
        GraphData a1 = new GraphData("ts","mail",6);
        GraphData b1 = new GraphData("mail","ts",6);
        GraphData c1 = new GraphData("o103","ts",8);
        GraphData d1 = new GraphData("o103","b3",4);
        GraphData e1 = new GraphData("o103","o109",12);
        GraphData f1 = new GraphData("o109","o103",12);
        GraphData g1 = new GraphData("o109","o119",16);
        GraphData h1 = new GraphData("o119","o109",16);
        GraphData i1 = new GraphData("o109","o111",4);
        GraphData j1 = new GraphData("o111","o109",4);
        GraphData k1 = new GraphData("b1","c2",3);
        GraphData l1 = new GraphData("b1","b2",6);
        GraphData m1 = new GraphData("b2","b1",6);
        GraphData n1 = new GraphData("b2","b4",3);
        GraphData o1 = new GraphData("b4","b2",3);
        GraphData p1 = new GraphData("b3","b1",4);
        GraphData q1 = new GraphData("b1","b3",4);
        GraphData r1 = new GraphData("b3","b4",7);
        GraphData s1 = new GraphData("b4","b3",7);
        GraphData t1 = new GraphData("b4","o109",7);
        GraphData u1 = new GraphData("c1","c3",8);
        GraphData v1 = new GraphData("c3","c1",8);
        GraphData w1 = new GraphData("c2","c3",6);
        GraphData x1 = new GraphData("c3","c2",6);
        GraphData y1 = new GraphData("c2","c1",4);
        GraphData z1 = new GraphData("c1","c2",4);


        GraphData a2 = new GraphData("d1","d3",8);
        GraphData b2 = new GraphData("d3","d1",8);
        GraphData c2 = new GraphData("o125","d2",2);
        GraphData d2 = new GraphData("d2","o125",2);
        GraphData e2 = new GraphData("o123","o125",4);
        GraphData f2 = new GraphData("o125","o123",4);
        GraphData g2 = new GraphData("o123","r123",4);
        GraphData h2 = new GraphData("r123","o123",4);
        GraphData i2 = new GraphData("o119","o123",9);
        GraphData j2 = new GraphData("o123","o119",9);
        GraphData k2 = new GraphData("o119","storage",7);
        GraphData l2 = new GraphData("storage","o119",7);

        graph.addAll(Arrays.asList(a1,b1,c1,d1,e1,f1,g1,h1,i1,j1,k1,l1,m1,n1,o1,p1,q1,r1,s1,t1,u1,v1,w1,x1,y1,z1,a2,b2,c2,d2,e2,f2,g2,h2,i2,j2,k2,l2));
        return graph;
    }
}
