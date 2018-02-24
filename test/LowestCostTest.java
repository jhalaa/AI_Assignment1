import data.GraphNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class LowestCostTest {
    SearchStrategizer lowestCost = new LowestCost();


    @Test
    void lcsFromNodeToItselfShouldThrowError()  {
        try{
            lowestCost.search(Initialiser.initializeGraph(), new GraphNode("ts"), new GraphNode("ts"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Source and destination are the same");
        }
    }

    @Test
    void lcsFromUnknownNodeShouldThrowError()  {
        try{
            lowestCost.search(Initialiser.initializeGraph(), new GraphNode("ll"), new GraphNode("ts"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Start or goal node is not in the graph!");
        }
    }
    @Test
    void lcsToUnknownNodeShouldThrowError()  {
        try{
            lowestCost.search(Initialiser.initializeGraph(), new GraphNode("ts"), new GraphNode("ll"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Start or goal node is not in the graph!");
        }
    }

}
