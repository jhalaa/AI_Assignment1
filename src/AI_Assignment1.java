import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class AI_Assignment1 {
    public static void main(String[] args) {
        SearchStrategizer mySearch = null;
        Scanner s = new Scanner(System.in);
        List<GraphData> myGraph = Initialiser.initializeGraph();

        System.out.println("Please enter the search strategy.\n 1:dfs 2:bfs 3:lowest cost  4:best-first 5:deep-limited 6:iterative-deepening");
        int option = s.nextInt();

        switch (option) {
            case 1:
                mySearch = new Dfs();
                break;
            case 2:
                mySearch = new Bfs();
                break;
            case 3:
                mySearch = new LowestCost();
                break;
            case 4:
                mySearch = new BestFirst();
                break;
            case 5:
                mySearch = new DeepLimited();
                break;
            case 6:
                mySearch = new IterativeDeepening();
                break;
            default:
                mySearch = new Dfs();
                break;
        }

        mySearch.search(myGraph);

    }


}