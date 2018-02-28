import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IterativeDeepeningTest {
    SearchStrategizer iterativeDeepening = new IterativeDeepening();
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
    void idsFromNodeToItselfShouldThrowError()  {
        try{
            iterativeDeepening.search(Initialiser.initializeGraph(), new GraphNode("ts"), new GraphNode("ts"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Source and destination are the same");
        }
    }

    @Test
    void idsFromUnknownNodeShouldThrowError()  {
        try{
            iterativeDeepening.search(Initialiser.initializeGraph(), new GraphNode("ll"), new GraphNode("ts"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Start or goal node is not in the graph!");
        }
    }
    @Test
    void idsToUnknownNodeShouldThrowError()  {
        try{
            iterativeDeepening.search(Initialiser.initializeGraph(), new GraphNode("ts"), new GraphNode("ll"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Start or goal node is not in the graph!");
        }
    }

    @Test
    void idsNoResultFoundShouldThrowError()  {
        try{
            iterativeDeepening.search(Initialiser.initializeGraph(), new GraphNode("c2"), new GraphNode("b1"), true);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Search space is exhausted and No path founds!");
        }
    }

    @Test
    void idsWithLegthOne() throws IllegalArgumentException {
        List<List<GraphEdges>> search = iterativeDeepening.search(Initialiser.initializeGraph(), new GraphNode("mail"), new GraphNode("ts"), true);
        List<List<GraphEdges>> expected = new ArrayList<>();
        List<GraphEdges> le1 = new ArrayList<>();
        le1.add(e1);

        expected.add(le1);
        assertEquals(search,expected);
    }

    @Test
    void idsFromMailToO103OneSolution() throws IllegalArgumentException {
        List<List<GraphEdges>> search = iterativeDeepening.search(Initialiser.initializeGraph(), mail, o103, false);
        List<List<GraphEdges>> expected = new ArrayList<>();

        List<GraphEdges> le1 = new ArrayList<>();
        le1.add(e1);
        le1.add(e2);

        expected.add(le1);
        assertEquals(search, expected);
    }

    @Test
    void idsFromMailToO103MultipleSolution() throws IllegalArgumentException {
        List<List<GraphEdges>> search = iterativeDeepening.search(Initialiser.initializeGraph(), mail, o103, true);
        List<List<GraphEdges>> expected = new ArrayList<>();

        List<GraphEdges> le1 = new ArrayList<>();
        le1.add(e1);
        le1.add(e2);

        expected.add(le1);
        assertEquals(search, expected);
    }

}
