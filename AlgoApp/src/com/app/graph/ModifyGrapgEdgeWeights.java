package com.app.graph;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;



// 2699. Modify Graph Edge Weights
// https://leetcode.com/problems/modify-graph-edge-weights


public class ModifyGrapgEdgeWeights {

static final int INF = (int)2e9; //Not necessary for most test cases. Can have Integer.MAX_VALUE;
       
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
               
        // <Source, <Dest, Distance>>       
        Map<Integer,Map<Integer,Integer>> graph = new HashMap<>(); 

                
        // Initialize graph with initial weights (replace -1 with max number temporarily)
        for (int[] edge : edges) {

            int weight = (edge[2] == -1) ? INF : edge[2];
            int src = edge[0], dest = edge[1];
            
            graph.putIfAbsent(edge[0], new HashMap<>());
            graph.putIfAbsent(edge[1], new HashMap<>());

            graph.get(src).put(dest,weight);
            graph.get(dest).put(src,weight);
        }

        // Find the initial shortest path
        DijkstraAlgo dks = new DijkstraAlgo(graph, source, n);
        
        long shortestDist1 = dks.dist[destination];

        //System.out.println("Sp 1 : " + shortestDist1);

        // If the target distance is not achievable
        if (shortestDist1 < target) return new int[0][0];
        
        boolean matchesTargetValue = (shortestDist1 == target);

        // Try to adjust the -1 edges to achieve the target distance
        for (int[] edge : edges) {
            
            if(edge[2] > 0) continue;                 

            edge[2] = matchesTargetValue ? INF : 1;
                           
            Map<Integer,Integer> n1 = graph.get(edge[0]);
            n1.put(edge[1], edge[2]);

            Map<Integer,Integer> n2 = graph.get(edge[1]);
            n2.put(edge[0], edge[2]);

               
            if(!matchesTargetValue){

                DijkstraAlgo dksUpdated = new DijkstraAlgo(graph, source,n);
                long shortestDistUpdated = dksUpdated.dist[destination];

                //System.out.println("Sp 2 : " + shortestDistUpdated);


                if (shortestDistUpdated <= target) {
                    
                    matchesTargetValue = true;

                    edge[2] += target - shortestDistUpdated;
                }

            }               
                        
        }

        return matchesTargetValue ? edges : new int[0][0];
           
    }


    // Straight forward Dikstra algo
    class DijkstraAlgo {

        Map<Integer,Map<Integer,Integer>> graph;
        PriorityQueue<Pair<Integer,Long>> pq = new PriorityQueue<>(Comparator.comparing(Pair::getValue));
        long[] dist;

        public DijkstraAlgo(Map<Integer,Map<Integer,Integer>> graph,int source, int n) {
            
            this.graph = graph;
            
            dist = new long[n];
        
            Arrays.fill(dist, INF);
        
            dist[source] = 0L;

            pq.add(new Pair(source, 0L));
        
            findShortestPath();
        }

        public void findShortestPath() {
        
           // System.out.println( "Graph :" +graph);

            while (!pq.isEmpty()) {
            
                Pair<Integer,Long> p = pq.poll();

                int curNode = p.getKey();
                long curDist = p.getValue();

                // Already a better shorter path exists
                if (curDist > dist[curNode]) continue;

                if(!graph.containsKey(curNode)) continue;

                // Relax each edge for vertex 
                
                Map<Integer,Integer> neigh = graph.get(curNode);
                
                for (Integer edgeKey : neigh.keySet()) {

                    int destNode = edgeKey;
                    long destDist = neigh.get(edgeKey);
                    
                    if(dist[destNode] > destDist + curDist){

                        dist[destNode] = destDist + curDist;

                        pq.add(new Pair(destNode,dist[destNode]));
                    }

                }
            }
        }    

 

    
    }


}
