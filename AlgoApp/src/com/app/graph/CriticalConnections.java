package com.app.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// 1192. Critical Connections in a Network https://leetcode.com/problems/critical-connections-in-a-network/

public class CriticalConnections {


    Map<Integer, List<Integer>> graph;
    boolean[] visited;
    
    int timeStamp = 0;    
    int[] discoveryTime; // Sequence in which nodes are discovered
    int[] low ; // Smallest discovery time of any node


    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        low = new int[n];
        visited = new boolean[n];
        discoveryTime = new int[n];
        graph = new HashMap<>();

        //Arrays.fill(low, -1);

        //Graph - adj list
         
        for(List<Integer> con : connections){

            int src = con.get(0), des = con.get(1);            

            graph.putIfAbsent(src, new ArrayList<>());
            graph.get(src).add(des);

            graph.putIfAbsent(des, new ArrayList<>());
            graph.get(des).add(src);
        }
        
        
        dfs(0, -1, timeStamp);

        return res;
                
    }
    

    private void dfs(int node, int parent, int timeStamp){

        visited[node] = true;

        discoveryTime[node] =  low[node] = timeStamp;
       

        for(int neighbor : graph.get(node)){

            if(neighbor == parent) continue; // Skip edges leading back to parent

            if(!visited[neighbor]) {

                dfs(neighbor, node, timeStamp + 1);

                low[node] = Math.min(low[node], low[neighbor]);

                // Check if an edge is a bridge
                if(low[neighbor] > discoveryTime[node]){
                    res.add(List.of(neighbor, node));
                }
            } else {

                low[node] = Math.min(low[node], discoveryTime[neighbor]);
            }          
            
        }

    }

}
