package graphs;

import java.util.*;

@SuppressWarnings("ALL")
public class code {
    static void depthFirstPrint(Map<String, List<String>> graph, String source){
        Stack<String> stack = new Stack<>();
        stack.push(source);
        while (!stack.isEmpty()) {
            String current = stack.pop();
            System.out.print(current + " ");
            for(String neighbor : graph.get(current)) stack.push(neighbor);
        }
    }
    static void depthFirstPrintRecursive(Map<String, List<String>> graph, String source){
        System.out.print(source + " ");
        for(String neighbor : graph.get(source))
            depthFirstPrintRecursive(graph, neighbor);
    }
    static void breathFirstPrint(Map<String, List<String>> graph, String source) {
        Queue<String> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()){
            String current = queue.poll();
            System.out.print(current + " ");
            for (String neighbor : graph.get(current)) queue.add(neighbor); // queue.addAll(graph.get(current));
        }
    }
    static boolean hasPathDFS(Map<String, List<String>> graph, String source, String destination){
        if (source.equals(destination)) return true;
        for (String neighbor : graph.get(source))
            if (hasPathDFS(graph, neighbor, destination)) return true;
        return false;
    }
    static boolean hasPathBFS(Map<String, List<String>> graph, String source, String destination){
        Queue<String> queue = new LinkedList<>();
        queue.add(source);
        while (!queue.isEmpty()){
            String current = queue.poll();
            if (current.equals(destination)) return true;
            queue.addAll(graph.get(current)); // you could also iterate through and add one by one
        }
        return false;
    }
    static boolean hasPathDFS(Map<String, List<String>> graph, String source, String destination, Set<String> set){ // undirected graph
        if (source.equals(destination)) return true;
        if (set.contains(source)) return false;
        set.add(source);
        for (String neighbor : graph.get(source))
            if (hasPathDFS(graph, neighbor, destination, set)) return true;
        return false;
    }
    static Map<String, List<String>> buildGraph(List<List<String>> edges){
        Map<String, List<String>> graph = new HashMap<>();
        for(List<String> edge : edges) {
            String source = edge.get(0);
            String destination = edge.get(1);
            if(!graph.containsKey(source)) graph.put(source, new ArrayList<>());
            if (!graph.containsKey(destination)) graph.put(destination, new ArrayList<>());
            graph.get(source).add(destination);
            graph.get(destination).add(source);
        }
        return graph;
    }
    static Map<Integer, List<Integer>> buildGraphInteger(List<List<Integer>> edgesList){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(List<Integer> edges : edgesList) {
            Integer source = edges.get(0);
            Integer destination = edges.get(1);
            if(!graph.containsKey(source)) graph.put(source, new ArrayList<>());
            if (!graph.containsKey(destination)) graph.put(destination, new ArrayList<>());
            graph.get(source).add(destination);
            graph.get(destination).add(source);
        }
        return graph;
    }
    static boolean undirectedPath(List<List<String>> edges, String source, String destination){
        Map<String, List<String>> graph = buildGraph(edges);
        return hasPathDFS(graph, source, destination, new HashSet<>());
    }
    static int countConnectedComponents(Map<Integer, List<Integer>> graph) {
        Set<Integer> visited = new HashSet<>();
        int count = 0;
        for (int vertex : graph.keySet()){
            if (explore(graph, vertex, visited))
                count = count + 1;
        }
        return count;
    }
    static boolean explore(Map<Integer, List<Integer>> graph, int vertex, Set<Integer> visited){
        if (visited.contains(vertex)) return false;
        visited.add(vertex);
        for (int neighbor : graph.get(vertex))
            explore(graph, neighbor, visited);
        return true;
    }
    static int longestComponent(Map<Integer, List<Integer>> graph){
        int longest = 0;
        Set<Integer> visited = new HashSet<>();
        for(int vertex : graph.keySet()){
            int count = exploreSize(graph, vertex, visited);
            if (count > longest) longest = count;
        }
        return longest;
    }
    static int exploreSize(Map<Integer, List<Integer>> graph, int vertex, Set<Integer> visited){
        if (visited.contains(vertex)) return 0;
        visited.add(vertex);
        int size = 1;
        for (int neighbor : graph.get(vertex)) {
            size += exploreSize(graph, neighbor, visited);
        }
        return size;
    }
    static int shortestPath(List<List<Integer>> edges, int source, int destination){
        Map<Integer, List<Integer>> graph = buildGraphInteger(edges);
        Queue<int[]> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(source);
        queue.add(new int[]{ source, 0});
        while (!queue.isEmpty()){
            int[] current = queue.poll();
            int vertex = current[0];
            int distance = current[1];
            if (vertex == destination) return distance;
            for (int neighbor : graph.get(vertex)){
                if (!visited.contains(neighbor)){
                    visited.add(neighbor);
                    queue.add(new int[] { neighbor, distance + 1});
                }
            }
        }
        return -1;
    }
    static int islandCount(int[][] grid){
        Set<String> visited = new HashSet<>();
        int count = 0;
        for (int row = 0; row < grid.length; row++){
            for (int column = 0; column < grid[0].length; column++){
                if (exploreIsland(grid, row, column, visited)) count++;
            }
        }
        return count;
    }
    static boolean exploreIsland(int[][] grid, int row, int column, Set<String> visited) {
        boolean rowInbounds = 0 <= row && row < grid.length;
        boolean columnInbounds = 0 <= column && column < grid[0].length;
        if (!rowInbounds || !columnInbounds) return false;
        if (grid[row][column] == 'W') return false;
        String position = row + "," + column;
        if (visited.contains(position)) return false;
        visited.add(position);
        exploreIsland(grid, row - 1, column, visited);
        exploreIsland(grid, row, column - 1, visited);
        exploreIsland(grid, row + 1, column, visited);
        exploreIsland(grid, row, column + 1, visited);
        return true;
    }
    static int minimumIslandCount(int[][] grid){
        Set<String> visited = new HashSet<>();
        int minimum = Integer.MAX_VALUE;
        for (int row = 0; row < grid.length; row++){
            for (int col = 0; col < grid[0].length; col++){
                int count = exploreIslandSize(grid, row, col, visited);
                if (count > 0 && count < minimum) minimum = count;
            }
        }
        return minimum;
    }
    static int exploreIslandSize(int[][] grid, int row, int col, Set<String> visited){
        boolean rowInbounds = 0 <= row && row < grid.length;
        boolean columnInbounds = 0 <= col && col < grid[0].length;
        if (!rowInbounds || !columnInbounds) return 0;

        if (grid[row][col] == 0) return 0;
        String position = row + "," + col;
        if (visited.contains(position)) return 0;
        visited.add(position);

        int count = 1;
        count += exploreIslandSize(grid, row + 1, col, visited);
        count += exploreIslandSize(grid, row - 1, col, visited);
        count += exploreIslandSize(grid, row, col + 1, visited);
        count += exploreIslandSize(grid, row, col - 1, visited);
        return count;
    }
    static int closestCarrot (char[][] grid, int startRow, int startCol){
        Set<String> visited = new HashSet<>();
        visited.add(startRow + "," + startCol);
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] { startRow, startCol, 0});
        while (!queue.isEmpty()) {
            int[] currentNode = queue.poll();
            int row = currentNode[0];
            int col = currentNode[1];
            int distance = currentNode[2];
            if (grid[row][col] == 'C') return distance;
            int[][] deltas = {{1,0}, {-1,0}, {0,1}, {0, -1}};
            for (int[] delta : deltas) {
                int neighborRow = row + delta[0];
                int neigbborCol = col + delta[1];
                boolean rowInbounds = 0<= neighborRow && neighborRow < grid.length;
                boolean columnInbounds = 0 <= neigbborCol && neigbborCol < grid[0].length;
                String neighborPosition = neighborRow + "," + neigbborCol;
                if (rowInbounds && columnInbounds && !visited.contains(neighborPosition) && grid[neighborRow][neigbborCol] != 'X') {
                    visited.add(neighborPosition);
                    queue.add(new int[] { neighborRow, neigbborCol, distance + 1});
                }
            }
        }
        return -1;
    }
    static int longestPath(Map<String, List<String>> graph){
        Map<String, Integer> distance = new HashMap<>();
        // mark terminal nodes with 0 distance
        for (String node : graph.keySet())
            if (graph.get(node) == null || graph.get(node).size() == 0)
                distance.put(node,0);
        // run DFS traversal after you know your terminal nodes
        for (String node : graph.keySet())
            traverseDistance(graph, node, distance);
        return Collections.max(distance.values());
    }
    static int traverseDistance(Map<String, List<String>> graph, String node, Map <String, Integer> distance ){
        if (distance.containsKey(node)) return distance.get(node);
        int maxDistance = 0;
        for (String neighbor : graph.get(node)) {
            int attempt = traverseDistance(graph, neighbor, distance);
            if (attempt > maxDistance) maxDistance = attempt;
        }
        distance.put(node, maxDistance + 1);
        return distance.get(node);
    }
    static int semesterRequired(int numCourses, int[][] prereqs){
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prereqs);
        Map<Integer, Integer> distance = new HashMap<>();
        for (Integer node : graph.keySet())
            if (graph.get(node) == null || graph.get(node).size() == 0)
                distance.put(node, 1);
        for(int i = 0; i < numCourses; i++)
            traverseDistance(graph, i, distance);
        return Collections.max(distance.values());
    }
    static int traverseDistance(Map<Integer, List<Integer>> graph, int node, Map<Integer, Integer> distance){
        if (distance.containsKey(node)) return distance.get(node);
        int maxDistance = 0;
        for (Integer neighbor : graph.get(node)){
            int neighborDistance = traverseDistance(graph, neighbor, distance);
            if (neighborDistance > maxDistance) maxDistance = neighborDistance;
        }
        distance.put(node, maxDistance + 1);
        return distance.get(node);
    }
    static Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prereqs){
        Map<Integer, List<Integer>>  graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) graph.put(i, new ArrayList<>());
        for (int[] prereq : prereqs) {
            int source = prereq[0];
            int destination = prereq[1];
            graph.get(source).add(destination);
        }
        return graph;
    }
    static boolean prereqsPossible(int numCourses, int[][] prereqs){
        // white-grey-black cycle detection algorithm
        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prereqs);
        for (Integer node : graph.keySet()){
            if (hasCycle(graph, node, visiting, visited))
                return false;
        }
        return true;
    }
    static boolean hasCycle(Map<Integer, List<Integer>> graph, int node, Set<Integer> visiting, Set<Integer> visited){
        if (visited.contains(node)) return false;
        if (visiting.contains(node)) return true;
        visiting.add(node);
        for (int neighbor : graph.get(node)){
            if (hasCycle(graph, neighbor, visiting, visited))
                return true;
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }
    static int bestBridge(char[][] grid) {
        // find one of the island
        // BFS towards the other island
        // count the distance
        // profit
        Set<String> mainIsland = new HashSet<>(); // a set containing all positions of the main island
        for (int row = 0; row < grid.length; row++){
            for (int column = 0; column < grid[0].length; column++ ){
                Set<String> possibleIsland = traverseIsland(grid, row, column, new HashSet<>());
                if (possibleIsland.size() > 0) {
                    mainIsland = possibleIsland;
                    break;
                }
            }
        }

        Set<String> visited = new HashSet<>(mainIsland);
        Queue<int[]> queue = new LinkedList<>();
        for (String position : mainIsland) {
            String[] gridPositon = position.split(",");
            int row = Integer.parseInt(gridPositon[0]);
            int column = Integer.parseInt(gridPositon[1]);
            queue.add(new int[] { row, column, 0 });
        }

        while (!queue.isEmpty()){
            int[] currentPosition = queue.poll();
            int row = currentPosition[0];
            int column = currentPosition[1];
            int distance = currentPosition[2];
            String position = row + "," + column;
            if (grid[row][column] == 'L' && !mainIsland.contains(position)) return distance - 1;
            int[][] deltas = {{0,1}, {0,-1}, {1,0}, {-1,0}};
            for (int[] delta : deltas){
                int neighborRow = row + delta[0];
                int neighborColumn = column + delta[1];
                String neighborPosition = neighborRow + "," + neighborColumn;
                if (isInbounds(grid, neighborRow, neighborColumn) && !visited.contains(neighborPosition)) {
                    queue.add(new int[] { neighborRow, neighborColumn, distance + 1});
                    visited.add(neighborPosition);
                }
            }
        }
        return -1;
    }
    static Set<String> traverseIsland(char[][] grid, int row, int column, Set<String> visited){
        if (!isInbounds(grid, row, column) || grid[row][column] == 'W') return visited;
        String position = row + "," + column;
        if (visited.contains(position)) return visited;
        visited.add(position);
        traverseIsland(grid, row - 1, column, visited);
        traverseIsland(grid, row + 1,column, visited);
        traverseIsland(grid, row, column - 1, visited);
        traverseIsland(grid, row, column + 1, visited);
        return visited;
    }
    static boolean isInbounds(char[][] grid, int row, int column){
        boolean rowInbounds = 0 <= row && row < grid.length;
        boolean columnInbounds = 0 <= column && row < grid[0].length;
        return rowInbounds && columnInbounds;
    }
    static boolean hasCycle(Map<Integer, List<Integer>> graph){
        // white-grey-black cycle detection algorithm
        Set<Integer> visiting = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int node : graph.keySet()) {
            if (cycleDetect(graph, node, visiting, visited))
                return true;
        }
        return false;
    }
    static boolean cycleDetect(Map<Integer, List<Integer>> graph, int node, Set<Integer> visiting, Set<Integer> visited){
        if (visited.contains(node)) return false;
        if (visiting.contains(node)) return true;
        visiting.add(node);
        for(int neighbor : graph.get(node)){
            if (cycleDetect(graph, neighbor, visiting, visited))
                return true;
        }
        visiting.remove(node);
        visited.add(node);
        return false;
    }
    static List<Integer> topologicalOrder(Map<Integer, List<Integer>> graph){
        // RECORD IN-DEGREE OF EACH VERTEX
        // track nodes and number of unvisited parents ; node : parent count initialized to 0 i.e all nodes start with zero parents
        Map<Integer, Integer> numParents = new HashMap<>();
        for (Integer node : graph.keySet()){
            numParents.put(node, 0);
        }
        for(Integer node : graph.keySet()){
            // iterate through all the childrent that this node points to
            for (Integer neighborNode : graph.get(node)) // increment the number of parent for the child
                numParents.put(neighborNode, numParents.getOrDefault(neighborNode, 0) + 1);
        }

        // ADD ALL VERTICES WITH 0 IN-DEGREE TO THE QUEUE
        // initialize ready list with all the nodes that have no parent to begin with
        List<Integer> ready = new ArrayList<>();
        for (Integer node : numParents.keySet()){
            if (numParents.get(node) == 0) {
                ready.add(node);
            }
        }

        List<Integer> order = new ArrayList<>();
        // Process until the Q becomes empty
        while (ready.size() > 0) {
            int currentNode = ready.remove(ready.size() - 1);
            order.add(currentNode);
            // Reduce the in-degree of each neighbor by 1
            for(Integer neighborNode : graph.get(currentNode) ) {
                numParents.put(neighborNode, numParents.getOrDefault(neighborNode, 0) - 1);
                // If in-degree of a neighbor becomes 0, add it to the Q
                if (numParents.get(neighborNode) == 0)
                    ready.add(neighborNode);
            }
        }

        return order;
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraphI(numCourses, prerequisites);
        Map<Integer, Integer> numParents = new HashMap<>();
        for (Integer node : graph.keySet()) numParents.put(node, 0);
        for(Integer node : graph.keySet()){
            for (Integer neighborNode : graph.get(node))
                numParents.put(neighborNode, numParents.getOrDefault(neighborNode, 0) + 1);
        }

        List<Integer> ready = new ArrayList<>();
        for (Integer node : numParents.keySet()){
            if (numParents.get(node) == 0) {
                ready.add(node);
            }
        }

        List<Integer> order = new ArrayList<>();
        while (ready.size() > 0) {
            int currentNode = ready.remove(ready.size() - 1);
            order.add(currentNode);
            for(Integer neighborNode : graph.get(currentNode) ) {
                numParents.put(neighborNode, numParents.getOrDefault(neighborNode, 0) - 1);
                if (numParents.get(neighborNode) == 0)
                    ready.add(neighborNode);
            }
        }

        if(order.size() == numCourses) return order.stream().mapToInt(i -> i).toArray();

        return new int[0];
    }
    static Map<Integer, List<Integer>> buildGraphI(int numCourses, int[][] prereqs){
        Map<Integer, List<Integer>>  graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) graph.put(i, new ArrayList<>());
        for (int[] prereq : prereqs) {
            int destination = prereq[0];
            int source = prereq[1];
            graph.get(source).add(destination);
        }
        return graph;
    }
    static String safeCracking(int[][] hints){
        Map<Integer, List<Integer>> graph = buidGraph(hints);
        return topologicalOrderI(graph);
    }
    static String topologicalOrderI(Map<Integer, List<Integer>>  graph){
        Map<Integer, Integer> numParents = new HashMap<>();
        for (int node : graph.keySet()) numParents.put(node, 0);
        for (int node : graph.keySet()){
            for (int neighbor : graph.get(node)){
                numParents.put(neighbor, numParents.getOrDefault(neighbor, 0) + 1);
            }
        }
        List<Integer> ready = new ArrayList<>();
        for (int node : numParents.keySet()){
            if (numParents.get(node) == 0) ready.add(node);
        }
        StringBuilder order = new StringBuilder();
        while (ready.size() > 0) {
            int currentNode = ready.remove(ready.size() - 1);
            order.append(currentNode);
            for (int neiggborNode : graph.get(currentNode)) {
                numParents.put(neiggborNode, numParents.getOrDefault(neiggborNode, 0) - 1);
                if (numParents.get(neiggborNode) == 0) ready.add(neiggborNode);
            }
        }
        return order.toString();
    }
    static Map<Integer, List<Integer>> buidGraph(int[][] hints){
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] hint : hints){
            int source = hint[0];
            int destination = hint[1];
            if (!graph.containsKey(source)) graph.put(source, new ArrayList<>());
            if (!graph.containsKey(destination)) graph.put(destination, new ArrayList<>());
            graph.get(source).add(destination);
        }
        return graph;
    }
    static List<String> findWords(char[][] board, String[] words) {
        List<String> result = new ArrayList<>();
        for(String word: words){
            if(exist(board, word)) result.add(word);
        }
        return result;
    }
    static boolean exist(char[][] board, String word) {
        for (int row = 0; row < board.length; row++){
            for (int column = 0; column < board[0].length; column++){
                if (exploreWord(board, word, row, column, 0))
                    return true;
            }
        }
        return false;
    }
    static boolean exploreWord(char[][] board, String word, int row, int column, int index){
        if(index >= word.length()) return true;
        if(word.length() == 0) return true;
        boolean rowInbounds = 0 <= row && row < board.length;
        boolean columnInbounds = 0 <= column && column < board[0].length;
        if (!rowInbounds || !columnInbounds) return false;
        if(board[row][column] != word.charAt(index)) return false;
        char temp = board[row][column];
        // use this instead of visited set to reduce complexity
        board[row][column] = '#';
        boolean result = (exploreWord(board, word, row + 1, column, index + 1) ||
                exploreWord(board, word, row - 1, column, index + 1) ||
                exploreWord(board, word, row , column + 1, index + 1) ||
                exploreWord(board, word, row , column - 1, index + 1)
        );
        board[row][column] = temp;
        return result;
    }
    static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int currentColor = image[sr][sc];
        if(currentColor != newColor)
            fillImage(image, sr, sc, currentColor, newColor);
        return image;
    }
    static void fillImage(int[][] image, int row, int column, int oldColor, int newColor){
        boolean rowInbounds = 0 <= row && row < image.length;
        boolean columnInbounds = 0 <= column && column < image[0].length;
        if(rowInbounds && columnInbounds && image[row][column] == oldColor) {
            image[row][column] = newColor;
            fillImage(image, row - 1, column, oldColor, newColor);
            fillImage(image, row, column - 1, oldColor, newColor);
            fillImage(image, row + 1, column, oldColor, newColor);
            fillImage(image, row, column + 1, oldColor, newColor);
        }
    }
    static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Queue<String> queue = new LinkedList<>();
        Set<String> words = new HashSet<>(wordList);
        words.remove(beginWord);
        queue.add(beginWord);
        int level = 0;
        while(!queue.isEmpty()){
            int size = queue.size();
            level++;
            for(int i = 0; i < size; i++){
                String currentWord = queue.poll();
                if(currentWord.equals(endWord)) return level;
                List<String> neighbors = generateNeighbors(currentWord);
                for(String neighbor : neighbors){
                    if(words.contains(neighbor)){
                        words.remove(neighbor);
                        queue.add(neighbor);
                    }
                }
            }
        }
        return 0;
    }
    static List<String> generateNeighbors(String s){
        List<String> result = new ArrayList<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++){
            char temp = chars[i];
            for (char c = 'a'; c <= 'z'; c++){
                chars[i] = c;
                String neighbor = new String(chars);
                result.add(neighbor);
            }
        }
        return result;
    }

    public static void main(String[] args) {
// example graph using adjacency list
// graph = {
//         a : [ b, c],
//         b : [ d],
//         c : [e],
//         d : [f],
//         e : [],
//         f : []
//         }
        Map<String, List<String>> graph = new HashMap<>();
        graph.put("a", new ArrayList<>(Arrays.asList("b","c")));
        graph.put("b", new ArrayList<>(List.of("d")));
        graph.put("c", new ArrayList<>(List.of("e")));
        graph.put("d", new ArrayList<>(List.of("f")));
        graph.put("e", new ArrayList<>());
        graph.put("f", new ArrayList<>());

// edge list, connection in both directions
// best in convert them in adjacency list
// take care of inverse connection
//
//        edge list
//        edges : [
//        [i, j],
//        [k, i],
//        [m, k],
//        [k, l],
//        [o, n],
//        ]
//
// equivalent adjacency list (graph)
// graph = {
//         i : [ j, k],
//         j : [ i],
//         k : [i, m, l],
//         m : [k],
//         l : [k],
//         o : [n]
//         o : [o]
//         }

        List<List<String>> edgeList = new ArrayList<>();
        edgeList.add(new ArrayList<>(Arrays.asList("i", "j")));
        edgeList.add(new ArrayList<>(Arrays.asList("k", "i")));
        edgeList.add(new ArrayList<>(Arrays.asList("m", "k")));
        edgeList.add(new ArrayList<>(Arrays.asList("k", "l")));
        edgeList.add(new ArrayList<>(Arrays.asList("o", "n")));

// graph = {
//         0 : [ 8, 1, 5],
//         1 : [  0 ],
//         5 : [0, 8],
//         8 : [0, 5],
//         2 : [3, 4],
//         3 : [2 , 4]
//         4 : [3, 2]
//         }

        Map<Integer, List<Integer>> connectedComponent = new HashMap<>();
        connectedComponent.put(0, new ArrayList<>(Arrays.asList(8, 1, 5)));
        connectedComponent.put(1, new ArrayList<>(List.of(0)));
        connectedComponent.put(5, new ArrayList<>(Arrays.asList(0, 8)));
        connectedComponent.put(8, new ArrayList<>(Arrays.asList(0, 5)));
        connectedComponent.put(2, new ArrayList<>(Arrays.asList(3, 4)));
        connectedComponent.put(3, new ArrayList<>(Arrays.asList(2, 4)));
        connectedComponent.put(4, new ArrayList<>(Arrays.asList(3, 2)));

        System.out.println(countConnectedComponents(connectedComponent));

        System.out.println(longestComponent(connectedComponent));

    }
}
