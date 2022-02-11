package graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class trial {

    static Map<Integer, List<Integer>> buildGraph(int[][] edges){
        Map<Integer, List<Integer>>  graph = new HashMap<>();

        for (int i = 0; i < edges.length; i ++){
            int[] edge = edges[i];
            int numberOfEdges = edge.length;
            if (!graph.containsKey(i)) graph.put(i, new ArrayList<>());
            for (int k : edge) {
                graph.get(i).add(k);
            }
        }

        return graph;
    }

    static void depthFirstSearch(Map<Integer, List<Integer>> graph, Integer source){
        System.out.print(source + " ");
        List<Integer> neighbors = graph.get(source);
        if (neighbors != null) {
            for (Integer neighbor : neighbors)
                depthFirstSearch(graph, neighbor);
        }


    }

    public static void main(String[] args) {
        int[][] graph = {
                {1,2},
                {3},
                {}
        };

        Map<Integer, List<Integer>> graphN = buildGraph(graph);
        depthFirstSearch(graphN, 0);
    }
}
