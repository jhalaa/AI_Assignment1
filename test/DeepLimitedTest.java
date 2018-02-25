import data.GraphEdges;
import data.GraphNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DeepLimitedTest {
    SearchStrategizer deepLimited = new DeepLimited();
    GraphNode mail = new GraphNode("mail");
    GraphNode ts = new GraphNode("ts");
    GraphNode o103 = new GraphNode("o103");
    GraphNode o109 = new GraphNode("o109");
    GraphNode b1 = new GraphNode("b1");
    GraphNode b2 = new GraphNode("b2");
    GraphNode b3 = new GraphNode("b3");
    GraphNode b4 = new GraphNode("b4");

    GraphEdges e1 = new GraphEdges(mail,ts,6);
    GraphEdges e2 = new GraphEdges(ts,o103,8);
    GraphEdges e3 = new GraphEdges(o103,o109,12);
    GraphEdges e4 = new GraphEdges(o103,b3,4);
    GraphEdges e5 = new GraphEdges(b1,b2,6);
    GraphEdges e6 = new GraphEdges(b2,b4,3);
    GraphEdges e7 = new GraphEdges(b3,b1,4);
    GraphEdges e8 = new GraphEdges(b3,b4,7);
    GraphEdges e9 = new GraphEdges(b4,o109,7);


    @Test
    void dlsFromNodeToItselfShouldThrowError()  {
        try{
            deepLimited.search(Initialiser.initializeGraph(), new GraphNode("ts"), new GraphNode("ts"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Source and destination are the same");
        }
    }

    @Test
    void dlsFromUnknownNodeShouldThrowError()  {
        try{
            deepLimited.search(Initialiser.initializeGraph(), new GraphNode("ll"), new GraphNode("ts"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Start or goal node is not in the graph!");
        }
    }
    @Test
    void dlsToUnknownNodeShouldThrowError()  {
        try{
            deepLimited.search(Initialiser.initializeGraph(), new GraphNode("ts"), new GraphNode("ll"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Start or goal node is not in the graph!");
        }
    }

    @Test
    void dlsNoResultFoundShouldThrowError()  {
        try{
            deepLimited.search(Initialiser.initializeGraph(), new GraphNode("c2"), new GraphNode("b1"), true);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"No path Exists!");
        }
    }

    @Test
    void dlsWithLegthOne() throws IllegalArgumentException {
        List<List<GraphEdges>> search = deepLimited.search(Initialiser.initializeGraph(), new GraphNode("mail"), new GraphNode("ts"), true);
        List<List<GraphEdges>> expected = new ArrayList<>();
        List<GraphEdges> le1 = new ArrayList<>();
        le1.add(e1);

        expected.add(le1);
        assertEquals(search,expected);
    }

    @Test
    void dlsFromMailToO103OneSolution() throws IllegalArgumentException {
        List<List<GraphEdges>> search = deepLimited.search(Initialiser.initializeGraph(), mail, o103, false);
        List<List<GraphEdges>> expected = new ArrayList<>();

        List<GraphEdges> le1 = new ArrayList<>();
        le1.add(e1);
        le1.add(e2);

        expected.add(le1);
        assertEquals(search, expected);
    }

    @Test
    void dlsFromMailToO103MultipleSolution() throws IllegalArgumentException {
        List<List<GraphEdges>> search = deepLimited.search(Initialiser.initializeGraph(), mail, o103, true);
        List<List<GraphEdges>> expected = new ArrayList<>();

        List<GraphEdges> le1 = new ArrayList<>();
        le1.add(e1);
        le1.add(e2);

        expected.add(le1);
        assertEquals(search, expected);
    }

    @Test
    void dlsFromMailToO109MultipleSolution() throws IllegalArgumentException {
        List<List<GraphEdges>> search = deepLimited.search(Initialiser.initializeGraph(), mail, o109, true);
        List<List<GraphEdges>> expected = new ArrayList<>();

        List<GraphEdges> le1 = new ArrayList<>();
        List<GraphEdges> le2 = new ArrayList<>();
        List<GraphEdges> le3 = new ArrayList<>();
        le1.addAll(Arrays.asList(e1,e2,e3));
        le2.addAll(Arrays.asList(e1,e2, e4,e8,e9));
        le3.addAll(Arrays.asList(e1,e2, e4,e7,e5,e6,e9));

        expected.addAll(Arrays.asList(le1,le2,le3));
        assertEquals(search, expected);
    }



}
