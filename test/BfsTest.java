import data.GraphNode;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class BfsTest {
    SearchStrategizer bfs = new Bfs();


    @Test()
    void bfsFromNodeToItselfShouldThrowError()  {
       try{
           bfs.search(Initialiser.initializeGraph(), new GraphNode("ts"), new GraphNode("ts"), false);
       } catch (IllegalArgumentException e) {
           assertEquals(e.getMessage(),"Source and destination are the same");
       }
    }

}
