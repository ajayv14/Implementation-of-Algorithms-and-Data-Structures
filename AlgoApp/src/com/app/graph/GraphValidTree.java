package com.app.graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// LC 261 Graph Valid Tree https://leetcode.com/problems/graph-valid-tree


// Time O(N + E)
// Space O(N + E)

public class GraphValidTree {

    List<List<Integer>> adjList = new ArrayList<>();

    Set<Integer> seen = new HashSet<>();
    
    public boolean validTree(int n, int[][] edges) {

        if(edges.length != n - 1) return false;
       
        for(int i = 0; i < n; i++){
            adjList.add(new ArrayList<>());
        }    

        for(int[] edge : edges ){
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        dfs(0);

        return (seen.size() == n);
    }

    private void dfs(int node){

        if(seen.contains(node)) return;

        seen.add(node);

        for(Integer neighbor: adjList.get(node)){

            dfs(neighbor);
        }
    }


    /*
    A graph is a tree if it has exactly n - 1 edges for n nodes.

    Simple dfs and adjacencey list base graph representation.

    Check if a node has been visited, if not mark it visited. 
    */

}
