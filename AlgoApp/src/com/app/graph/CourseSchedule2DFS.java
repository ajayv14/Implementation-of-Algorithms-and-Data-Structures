package com.app.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class CourseSchedule2DFS {


    // Toposort by DFS : Check for cycle and get reverspost order
    public int[] findOrder(int numCourses, int[][] prerequisites) {
     

        Graph graph = new Graph(numCourses);

        for(int[] edge : prerequisites){
            graph.addEdge(edge[1], edge[0]);
            graph.E++;
        }

       // System.out.println(graph);    

        // Check for cycle 
       DAGCycle cycle = new DAGCycle();

       if(cycle.detectCycle(graph)) return new int[0];


        
        DFSOrder dOrder = new DFSOrder();

        // Get reversedpost order 
        Stack<Integer> reversed = dOrder.reverseOrder(graph);


        // convert stack to int[]
        int[] res = new int[reversed.size()];

        int p = 0;
        while(!reversed.isEmpty()){
            res[p] = reversed.pop();
            p++;
        }
        return res ;     
        
    }

    
    class Graph {
        int V;
        int E;
        List<Integer>[] adj;

        public Graph(int vertices){
            V = vertices;
            E = 0;

            adj = new ArrayList[V]; // cant create new ArrayList<>[]

            for(int i = 0 ; i < vertices; i++){
                adj[i] = new ArrayList<Integer>();
            }
        }

        public void addEdge(int v, int w){
            adj[v].add(w);
        }        

    }

   class DFSOrder {

        Stack<Integer> reversed = new Stack<>();
        boolean[] marked ;

        public Stack<Integer> reverseOrder(Graph g){

            marked = new boolean[g.V];
                        
            for(int v = 0; v < g.V ; v++){
                if(!marked[v]) dfs(g,v);
            }
            return reversed;

        }

        public void dfs(Graph g, int v){

            marked[v] = true;

            for(int w : g.adj[v]){
               
                if(!marked[w]) dfs(g, w);   

            }

            reversed.push(v);    

        }

    }

    class DAGCycle {

        boolean[] marked;
        boolean[] onStack;

        
        public boolean detectCycle(Graph g){

            marked = new boolean[g.V];
            onStack = new boolean[g.V];

            for(int v = 0; v < g.V; v++ ){
                if(!marked[v] && dfs(g, v)) return true;
            }

            return false;

        }

        private boolean dfs(Graph g, int v){

            marked[v] = true;
            onStack[v] = true;

            for(int w : g.adj[v]){

                if(onStack[w]) return true;

                else if (!marked[w] &&  dfs(g, w)) return true;
            }

            onStack[v] = false;    

            return false;

        }

    }

    /*
     * 
     * prerequisites = [[1,0]]  Expected [0,1]
     * 
     * prerequisites =[[1,0],[2,0],[3,1],[3,2]]   Expected[0,2,1,3]
     * 
     * prerequisites = [[1,0],[2,0],[0,2]] Expected []
     * 
     * prerequisites = [[0,1],[3,1],[1,3],[3,2]] Expected []     * 
     */

}
