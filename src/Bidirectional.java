import data.Graph;
import data.GraphEdges;
import data.GraphNode;

import java.util.*;
import java.util.stream.Collectors;

    public class Bidirectional implements SearchStrategizer {
        public List<List<GraphEdges>> search(Graph graph, GraphNode src, GraphNode dest, boolean searchMode) throws IllegalArgumentException {

            List<List<GraphEdges>> result = new ArrayList<>();

            // if source and destination are the same
            if(src.equals(dest))
                throw new IllegalArgumentException("Source and destination are the same");

            // adding source and destination to frontier
            Queue<List<GraphEdges>> start_frontier = new LinkedList<>();
            List initialNodes = graph.getEdges()
                    .stream()
                    .filter(edge -> edge.getFrom().equals(src))
                    .map(edge -> {List temp =new ArrayList(); temp.add(edge); return temp;})
                    .collect(Collectors.toList());
            start_frontier.addAll(initialNodes);

            // if a result of edge length one exists add to frontier
            if(start_frontier.stream().anyMatch(list -> list.get(0).getTo().equals(dest))){
                GraphEdges c = start_frontier.stream()
                        .filter(list -> list.get(0).getTo().equals(dest))
                        .flatMap(Collection::stream)
                        .findFirst()
                        .orElse(null);
                start_frontier.remove(Arrays.asList(c));
                result.add(Arrays.asList(c));
            }

            // adding dest nodes to goal_frontier
            Queue<List<GraphEdges>> dest_frontier = new LinkedList<>();
            List destNodes = graph.getEdges()
                    .stream()
                    .filter(edge -> edge.getTo().equals(dest))
                    .map(edge -> {List temp =new ArrayList(); temp.add(edge); return temp;})
                    .collect(Collectors.toList());
            dest_frontier.addAll(destNodes);

            // if source or destination not present in graph
            if (!graph.getNodes().contains(src) || !graph.getNodes().contains(dest)) {
                throw new IllegalArgumentException("Start or goal node is not in the graph!");
            }

            boolean collisionChecker = false;
            Set<GraphNode> start_visited = new HashSet<>();
            start_visited.add(src);
            Set<GraphNode> dest_visited = new HashSet<>();
            dest_visited.add(dest);

            while (!start_frontier.isEmpty() && !dest_frontier.isEmpty() && !collisionChecker) {
                List<GraphEdges> curr_start = start_frontier.remove();
                List<GraphEdges> curr_dest = dest_frontier.remove();

                GraphNode lastNode = curr_start.get(curr_start.size() - 1).getTo();
                GraphNode firstNode = curr_dest.get(0).getFrom();
                // adding all the toNodes from lastNode to the start_frontier
                for (GraphEdges edge : graph.getEdges()) {
                    if (edge.getFrom().equals(lastNode) && !start_visited.contains(edge.getTo())) {
                        List<GraphEdges> temp = new ArrayList<>(curr_start);
                        temp.add(edge);
                        start_frontier.add(temp);
                    }

                }
                start_visited.add(lastNode);

                // adding all the fromNodes which to firstNode to the dest_frontier
                for (GraphEdges edge : graph.getEdges()) {
                    if (edge.getTo().equals(firstNode) && !dest_visited.contains(edge.getFrom())) {
                        List<GraphEdges> temp2 = new ArrayList<>(curr_dest);
                        temp2.add(0, edge);
                        dest_frontier.add(temp2);
                    }

                }
                dest_visited.add(firstNode);

                // check if the two frontiers collision
                if (dest_visited.contains(lastNode)) {
                    collisionChecker = true;
                    List<GraphEdges> start_edges = new ArrayList<>(curr_start);
                    List<GraphEdges> dest_edges = fromNodeCheck(dest_frontier, lastNode);
                    result.addAll(Arrays.asList(start_edges));
                    result.addAll(Arrays.asList(dest_edges));
                } else if (start_visited.contains(firstNode)) {
                    collisionChecker = true;
                    List<GraphEdges> dest_edges = new ArrayList<>(curr_dest);
                    List<GraphEdges> start_edges = toNodeCheck(start_frontier, firstNode);
                    result.addAll(Arrays.asList(start_edges));
                    result.addAll(Arrays.asList(dest_edges));
                }

            }

            // if result not found
            if(result.isEmpty())
                throw new IllegalArgumentException("No path Exists!");

            return result;
        }

        // helper function to get the list of edges that is from src to collision node in the start frontier
        private List<GraphEdges> toNodeCheck(Queue<List<GraphEdges>> start_frontier, GraphNode node) throws IllegalArgumentException {
            if (start_frontier.isEmpty()) {
                throw new IllegalArgumentException("start_frontier is Empty!");
            }
            while (!start_frontier.isEmpty()) {
                List<GraphEdges> curr = start_frontier.poll();
                int n = curr.size();
                for (int i = 0; i < n; i++) {
                    if (curr.get(i).getTo().equals(node)) {
                        List<GraphEdges> res = new ArrayList<>();
                        for (int j = 0; j <= i; j++) {
                            res.add(curr.get(j));
                        }
                        return res;
                    }
                }
            }
            throw new IllegalArgumentException("Node is not in the start_frontier!");
        }

        // helper function to get the list of edges that is from collision node to dest in the dest frontier
        private List<GraphEdges> fromNodeCheck(Queue<List<GraphEdges>> dest_frontier, GraphNode node) throws IllegalArgumentException {
            if (dest_frontier.isEmpty()) {
                throw new IllegalArgumentException("start_frontier is Empty!");
            }
            while (!dest_frontier.isEmpty()) {
                List<GraphEdges> curr = dest_frontier.poll();
                int n = curr.size();
                for (int i = 0; i < n; i++) {
                    if (curr.get(i).getFrom().equals(node)) {
                        List<GraphEdges> res = new ArrayList<>();
                        for (int j = i; j < n; j++) {
                            res.add(curr.get(j));
                        }
                        return res;
                    }
                }
            }
            throw new IllegalArgumentException("Node is not in the start_frontier!");
        }
    }
