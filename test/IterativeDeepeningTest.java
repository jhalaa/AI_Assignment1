import data.GraphNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class IterativeDeepeningTest {
    SearchStrategizer iterativeDeepening = new IterativeDeepening();


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

}
