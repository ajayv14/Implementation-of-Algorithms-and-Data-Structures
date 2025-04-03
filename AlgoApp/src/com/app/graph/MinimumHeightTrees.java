package com.app.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

// 310. Minimum Height Trees -  https://leetcode.com/problems/minimum-height-trees/

public class MinimumHeightTrees {

    // Modified Topo sort
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        
        List<Integer> res = new ArrayList<>();

        if(n == 1 ) {
            res.add(0);
            return res;
        }

        // Adj List
        Map<Integer, List<Integer>> graph = new HashMap<>();

        int[] indegree = new int[n];

        for(int[] edge : edges){

            graph.putIfAbsent(edge[0], new ArrayList<>());
            graph.putIfAbsent(edge[1], new ArrayList<>());

            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);

            indegree[edge[0]]++;
            indegree[edge[1]]++;
        }

        Queue<Integer> q = new LinkedListImplementation<>();    

        // Add all leaves
        for(int i = 0; i < indegree.length; i++){
            if(indegree[i] == 1) q.add(i);
        }

        // Only 2 centroids at max

        int processed = 0;

        while(n - processed > 2){

            int size = q.size();

            processed += size;

            for(int s = 0; s < size; s++){

                int node = q.remove();

                for(int neighbor : graph.get(node)){

                    indegree[neighbor]--;

                    if(indegree[neighbor] == 1)  q.add(neighbor);
                }
            }
        }


        // build result from centroids in queue

        res.addAll(q);

        return res;
        
    }
}
