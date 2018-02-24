import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.List;
import java.util.Scanner;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) throws IllegalArgumentException {
        SearchStrategizer mySearch = null;
        List<List<GraphEdges>> result = null;

        Scanner s = new Scanner(System.in);
        Graph myGraph = Initialiser.initializeGraph();

        System.out.println("Please enter the search strategy.\n 1:dfs 2:bfs 3:lowest cost  4:best-first 5:deep-limited 6:iterative-deepening 7:A -Star 8:Informal Search Using Heuristic ");
        int option = s.nextInt();

        System.out.println("Enter the search node");
        GraphNode src = new GraphNode(s.next());

        System.out.println("Enter the destination node");
        GraphNode dest = new GraphNode(s.next());

        System.out.println("Enter the search mode. \n 0: first-solution 1: all-solutions");
        int searchModeOption = s.nextInt();

        boolean searchMode = false;
        switch (searchModeOption) {
            case 0:
                searchMode = false;
                break;
            case 1:
                searchMode = true;
                break;
        }

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
            case 7:
                mySearch = new AStar();
                break;
            case 8:
                mySearch = new InformalSearchUsingHeuristic();
                break;
            default:
                mySearch = new Dfs();
                break;
        }

        try {
            result = mySearch.search(myGraph, src, dest, searchMode);
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            exit(0);
        }
        catch (Exception e) {
            System.out.println("An error has occured!Please try again.");
            exit(0);
        }
        System.out.println("The result is:");
        System.out.println(ResultFormatter.format(result));
    }
}