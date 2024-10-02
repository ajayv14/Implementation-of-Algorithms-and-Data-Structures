package com.app.graph;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;


// Directed edge weighted graph 
class EdgeWeightedDigraph {
     
                  
      List<WEdge> adj[]; // array of linked list (adjacency list)
      
      public EdgeWeightedDigraph(int V){
         
         adj = new ArrayList[V];
         
         for(int v = 0; v < V; v++){            
            adj[v] = new ArrayList<>();
         }
      }
      
      public void addEdge(int v, int v, int w){ // pos - initial position of the edge 
         
         G[pos].add(new Edge(v,w));  // create the Edge and add to list    
      }
      
      @Override
      public String toString(){
         
         String op =  "";
         
         for(int i = 0; i < G.length; i++){
         
            op += " Adjacency list : " + i + " neighbours : " + G[i] + "\n";
         }
         
         return op;
      }


      class WEdge {
              
         int v;
         int w; 
         double weight;
         
         public Edge(int v, int w, double weight){
            this.v = v;
            this.w = w;
            this.weight = weight;
         }

         public double weight(){
            return weight;
         }
         
         @Override          
         public String toString(){
             return " v,w : " + v + "," + w;
         }                  
      }

}


class GraphDemo{

   public static void main(String[] args){
      
      Graph graph = new Graph(5);
      
         graph.addEdge(0, 2, 40);
         graph.addEdge(0, 3, 30);
         graph.addEdge(2, 3, 25);
         graph.addEdge(3, 5, 50);
              
         System.out.println(graph);
      
   }

}