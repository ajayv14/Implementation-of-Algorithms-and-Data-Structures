package com.app.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


//2699. Modify Graph Edge Weights
//https://leetcode.com/problems/modify-graph-edge-weights/?source=submission-noac

public class ModifyGraphEdgeWeights {


        // Works partially

        /**
         * 
         * Fails at : 
         * 
         * 
         n = 4
        edges = [[3,0,-1],[1,2,-1],[2,3,-1],[1,3,9],[2,0,5]]
        source = 0
        destination = 1
        target = 7
         */

     public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {


        EdgeWeightedDigraph graph = new EdgeWeightedDigraph(n);

        
        for(int[] edge : edges){

            int weight = (edge[2] == -1) ? 1 : edge[2];                       
            graph.addEdge(edge[0],edge[1],weight);
            graph.addEdge(edge[1],edge[0],weight);
        }

        DijkstraAlgo dks = new DijkstraAlgo(graph,source);
        
        double shortestDist = dks.distTo[destination]; 

        System.out.println("Sh 1 - " + shortestDist);
        
        if(shortestDist > target) return new int[0][0];       
       

        if(shortestDist == target){

            for(int[] edge : edges){
                
                if(edge[2] == -1) edge[2] = 1;
            }    
            return edges;
        }  

        
           
        for(int[] edge : edges){
                
                if(edge[2] == -1) { 
                      
                     edge[2] = 1; 
                     

                    int optimizedWeight = target - (int)shortestDist;  
                    
                        optimizedWeight += edge[2];              
                     
                        graph.updateEdge(edge[0],edge[1],optimizedWeight);
                        graph.updateEdge(edge[1],edge[0],optimizedWeight);

                    DijkstraAlgo dks2 = new DijkstraAlgo(graph,source);

                    shortestDist = (int)dks2.distTo[destination];

                    System.out.println("sh 2 : " + shortestDist);

                    
                    if(shortestDist == target) {
                            
                            edge[2] = optimizedWeight;

                            for(int[] e : edges){
                
                                if(e[2] == -1) e[2] = 1;
                                
                            }                            
                            
                            return edges;                           
                    }

                    else{                        
                        graph.updateEdge(edge[0],edge[1],1);
                        graph.updateEdge(edge[1],edge[0],1);
                    } 
                }

        }            

        System.out.println("Can't find");

        return new int[0][0];
              

            
    }

    class DijkstraAlgo {

    PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingDouble(Node::distance));
    double[] distTo;

    public DijkstraAlgo(EdgeWeightedDigraph g, int source) {
        distTo = new double[g.V];
        
        for (int i = 0; i < g.V; i++) {
            distTo[i] = Double.POSITIVE_INFINITY;
        }
        
        distTo[source] = 0;
        pq.add(new Node(source, 0.0));
        
        findShortestPath(g);
    }

    public void findShortestPath(EdgeWeightedDigraph g) {
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            int v = node.vertex;

            // Avoid processing stale nodes
            if (node.distance > distTo[v]) continue;

            // Relax each edge for vertex `v`
            for (DirectedEdge edge : g.adj[v]) {
                relax(edge);
            }
        }
    }

    public void relax(DirectedEdge edge) {
        int v = edge.v;
        int w = edge.w;
        
        // Check if a shorter path to `w` is found via `v`
        if (distTo[w] > distTo[v] + edge.weight) {
            distTo[w] = distTo[v] + edge.weight;
            
            // Add updated node with new distance to the priority queue
            pq.add(new Node(w, distTo[w]));
        }
    }
}


 

    class DirectedEdge {

        int v;
        int w;
        double weight;

        public DirectedEdge(int v, int w, double weight){
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public double weight(){
            return weight;
        }

    }

    class EdgeWeightedDigraph {

        int V;
        int E;
        List<DirectedEdge>[] adj ;

        public EdgeWeightedDigraph(int V){

            this.V = V;
            adj = new ArrayList[V]; 

            for(int v = 0; v < V ; v++){
                adj[v] = new ArrayList<>();
            }
        }

        public void addEdge(int v, int w, double weight){
            adj[v].add(new DirectedEdge(v,w,weight));
        }


        public void updateEdge(int v, int w, double weight){
            
            for(DirectedEdge d : adj[v]){

                if(d.w == w){

                    d.weight = weight;

                }

            }
            
        }

    }

    class Node {

        int vertex;
        double distance;

        public Node(int vertex, double distance){
            this.vertex = vertex;
            this.distance = distance;
        }

        public double distance(){
            return distance;
        }

    }


}
