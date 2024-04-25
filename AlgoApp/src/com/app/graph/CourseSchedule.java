package com.app.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// LC 207   
// Toposort - // Kahn's algo'
public class CourseSchedule {

    // Kahn's algo'
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> graph = new HashMap<>();

        int[] inDegree = new int[numCourses]; // Incoming edges

        // Create graph
        for (int[] prereq : prerequisites) {

            List<Integer> list = graph.getOrDefault(prereq[1], new ArrayList<>()); // input[2 3] Incoming edge is coming
                                                                                   // from 3 -> 2.
            list.add(prereq[0]);
            graph.put(prereq[1], list);
        }

        // Increment incoming edges on each node listed in pre-reqs
        for (int[] prereq : prerequisites) {
            inDegree[prereq[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();

        // Pick the nodes with no incoming edges
        for (int i = 0; i < inDegree.length; i++) {

            if (inDegree[i] == 0)
                queue.offer(i);

        }

        int count = 0;

        while (!queue.isEmpty()) {

            int cur = queue.poll();

            if (inDegree[cur] == 0)
                count++;

            if (!graph.containsKey(cur))
                continue; // no more neighbors

            for (int neighbors : graph.get(cur)) {

                inDegree[neighbors]--;
                if (inDegree[neighbors] == 0)
                    queue.add(neighbors);
            }

        }

        return count == numCourses;

    }

    // Time limit exceeded 48/52 tests passed
    /*
     * public boolean canFinish(int numCourses, int[][] prerequisites) {
     * 
     * 
     * if(prerequisites == null || prerequisites.length == 0 ) return true;
     * 
     * boolean[] isVisited = new boolean[numCourses];
     * 
     * Map<Integer, List<Integer>> courseGraph = createGraph(prerequisites); //
     * Create a graph
     * 
     * for(Integer key : courseGraph.keySet()){
     * 
     * boolean res = dfs(isVisited, courseGraph, key);
     * 
     * if(!res) return res;
     * }
     * 
     * return true;
     * 
     * }
     * 
     * // Form the Graph
     * public Map<Integer,List<Integer>> createGraph(int[][] prerequisites){
     * 
     * Map<Integer,List<Integer>> courseMap = new HashMap<>();
     * 
     * for(int[] prereq : prerequisites){
     * 
     * if(!courseMap.containsKey(prereq[0])) courseMap.put(prereq[0], new
     * ArrayList<>());
     * 
     * courseMap.put(prereq[0], courseMap.get(prereq[0])).add(prereq[1]);
     * 
     * }
     * //System.out.println(courseMap);
     * 
     * return courseMap;
     * }
     * 
     * //
     * public boolean dfs(boolean[] isVisited, Map<Integer,List<Integer>>
     * courseGraph, int currentNode){
     * 
     * 
     * // Cycle exists
     * if(isVisited[currentNode]) return false;
     * 
     * else isVisited[currentNode] = true;
     * 
     * List<Integer> neighbors = courseGraph.get(currentNode);
     * 
     * if(neighbors != null) {
     * 
     * for(int neighbor : neighbors){
     * 
     * if(!dfs(isVisited, courseGraph, neighbor)) return false;;
     * }
     * 
     * }
     * isVisited[currentNode] = false; // reset
     * 
     * return true;
     * }
     */

    public static void main(String[] args) {

        CourseSchedule obj = new CourseSchedule();

        int n1 = 2;
        int[][] prereqs1 = new int[][] { { 1, 0 }, { 0, 1 } };

        System.out.println("Expected : false ");
        System.out.println("Actual : " + obj.canFinish(n1, prereqs1));

        int n2 = 20;
        int[][] prereqs2 = new int[][] { { 0, 10 }, { 3, 18 }, { 5, 5 }, { 6, 11 }, { 11, 14 }, { 13, 1 }, { 15, 1 },
                { 17, 4 } };

        System.out.println("Expected : false");
        System.out.println("Actual : " + obj.canFinish(n2, prereqs2));

        int n3 = 5;
        int[][] prereqs3 = new int[][] { { 1, 4 }, { 2, 4 }, { 3, 2 }, { 3, 1 } };

        System.out.println("Expected : true");
        System.out.println("Actual : " + obj.canFinish(n3, prereqs3));

    }

}
