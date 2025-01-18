package com.app.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC 323 https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/

public class ConnectedComponents {

    // Straightforward dfs

    boolean[] visited ;
    Map<Integer, List<Integer>> graph = new HashMap<>();


    public int countComponents(int n, int[][] edges) {

        visited = new boolean[n]; 
        

        // Build Undirected Graph

        for(int[] edge : edges){

            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.get(edge[0]).add(edge[1]);

            graph.putIfAbsent(edge[1], new ArrayList<>());
            graph.get(edge[1]).add(edge[0]);
        }
        

        int count = 0;

        for(int i = 0; i < n; i++){
            
            if(!visited[i]){
                count++;
                dfs(i);
            } 
        }

        return count;    
    }

    private void dfs(int node){
        
        visited[node] = true;

        for(int neighbor : graph.getOrDefault(node, Collections.emptyList())){

            if(!visited[neighbor]) dfs(neighbor);
        }
    }
}
