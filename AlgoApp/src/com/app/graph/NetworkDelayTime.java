package com.app.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class NetworkDelayTime {

    // Apply Single source shortest path - Dijkstra's ?

    public int networkDelayTime(int[][] times, int n, int k) {

         // Source vertex, <Dest vertex, distance>
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();

        // Create a graph
        for(int[] time : times){

            int source = time[0], dest = time[1], dist = time[2];

            graph.putIfAbsent(source, new ArrayList<>());

            graph.get(source).add(new Pair(dest, dist));
        }

        // Dijkstra's shortest path from single source
        
        int[] distFromSource = dijkstra(graph,n,k);

        for(int d : distFromSource){
            System.out.println(d);
        }


        // Time taken to reach farthest noide calc

        int maxValue = Arrays.stream(distFromSource).max().getAsInt();

        return (maxValue == Integer.MAX_VALUE) ? -1 : maxValue; 

    }



    private int[] dijkstra(Map<Integer, List<Pair<Integer, Integer>>> graph,int n, int k){


        // Min heap with distance comparator
        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((a,b)-> a.getValue() - b.getValue());

        // Maintain shortest distance - n nodes, labeled from 1 to n
        int[] dist = new int[n + 1];
        //boolean [] visited = new boolean[n + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        // Source
        dist[k] = 0;

        // n nodes, labeled from 1 to n, so ignoring index 0
        dist[0] = Integer.MIN_VALUE;

        pq.add(new Pair(k,0));


        while(!pq.isEmpty()){

            Pair<Integer, Integer> p = pq.remove();

            int curNode = p.getKey();
            int curNodeDist = p.getValue();

            //if(visited[curNode] == true) continue;

            // Mark visited
            //visited[curNode] = true;

            if(curNodeDist > dist[curNode] ) continue;
            
            if(!graph.containsKey(curNode)) continue;



            List<Pair<Integer, Integer>> neighbors = graph.get(curNode);

            for(Pair<Integer, Integer> neighbor : neighbors){

                int destNode = neighbor.getKey();
                int destNodeDist = neighbor.getValue();
                
                //Relaxation step
                if(dist[destNode] > curNodeDist + destNodeDist){
                    dist[destNode] = curNodeDist + destNodeDist;
                     // Pair with Updated distance
                    pq.add(new Pair(destNode, dist[destNode]));
                }

                //relax(); 
               
            }
            

        }

        return dist;

    }    



/*    private class Node {
        int id; 
        int distance;
    }*/


}
