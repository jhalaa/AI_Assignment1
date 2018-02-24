import data.GraphNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class LowestCostTest {
    SearchStrategizer lowestCost = new LowestCost();


    @Test()
    void bfsFromNodeToItselfShouldThrowError()  {
        try{
            lowestCost.search(Initialiser.initializeGraph(), new GraphNode("ts"), new GraphNode("ts"), false);
        } catch (IllegalArgumentException e) {
            assertEquals(e.getMessage(),"Source and destination are the same");
        }
    }

}
