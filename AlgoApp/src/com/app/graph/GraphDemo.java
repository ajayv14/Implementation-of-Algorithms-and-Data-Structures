package com.app.graph;

import java.util.List;
import java.util.LinkedList;

class Graph{
     
      class Edge{
                  
         int v; // end vertex
         int w; // weight
         
         public Edge(int v, int w){
            this.v = v;
            this.w = w;
         }
         
         @Override          
         public String toString(){
             return " v,w : " + v + "," + w;
         }                  
      }
      
      
      List<Edge> G[]; // array of linked list (adjacency list)
      
      public Graph(int size){
         
         G = new LinkedList [size];
         
         for(int i = 0; i < size; i++){
            
            G[i] = new LinkedList<Edge>();
         }
      }
      
      public void addEdge(int pos, int v, int w){ // pos - initial position of the edge 
         
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