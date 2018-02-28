[![Build Status](https://travis-ci.org/jhalaa/AI_Assignment1.svg?branch=master)](https://travis-ci.org/jhalaa/AI_Assignment1)
[![Maintainability](https://api.codeclimate.com/v1/badges/72af7ed4e1eb1b9dd0e7/maintainability)](https://codeclimate.com/github/jhalaa/AI_Assignment1/maintainability)
[![Coverage Status](https://coveralls.io/repos/github/jhalaa/AI_Assignment1/badge.svg?branch=master)](https://coveralls.io/github/jhalaa/AI_Assignment1?branch=master)
AI assignment1

Team Members

1)Ying Chen 2)Jhalaa Chinoy

All the programmings are coded and run in Java.
It includes a file of data for the Graph, GraphNode and GraphEdges 
and a Initialiser to create the Graph example in the assignment. 

It also has an Interface of SearchStrategizer to perform the generic 
function of difference searches and implement the search function.
Uninformed searches include Bfs, Dfs, LowestCost, DeepLimited, 
Iterative Deepening and Bidirectional. Informed searches include BestFirst, 
InformedSearchUsingHeuristic and AStar. HeuristicCreater is to create
Heuristic functions for the Heuristic searches.

The Main class is to run the program. When clicking on run, the console
will ask you to input the search strategy, start node, dest node, and
search mode. According to the instructions, you can enter 0 - 9 search
strategies, any node in the graph to specify the start and destination,
and 0 or 1 to specify the search mode. After inputing all the arguments,
the result path(s) will print out.

Besides the part 1 and part 2 for the assignment, bonus points would be
part 3 to implement the Bidirectional Search.