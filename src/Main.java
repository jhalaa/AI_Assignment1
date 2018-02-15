import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SearchStrategizer mySearch = null;
        Scanner s = new Scanner(System.in);
        Graph myGraph = Initialiser.initializeGraph();

        System.out.println("Please enter the search strategy.\n 1:dfs 2:bfs 3:lowest cost  4:best-first 5:deep-limited 6:iterative-deepening");
        int option = s.nextInt();

        System.out.println("Enter the search node");
        GraphNode src = new GraphNode(s.next());

        System.out.println("Enter the destination node");
        GraphNode dest = new GraphNode(s.next());

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

        mySearch.search(myGraph,src,dest);

    }


}