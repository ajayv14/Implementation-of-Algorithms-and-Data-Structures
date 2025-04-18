package com.app.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

//credits : https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/discuss/665239/Simple-Java-Solution
//credits 2 : https://leetcode.com/problems/reorder-routes-to-make-all-paths-lead-to-the-city-zero/

/**
  * <a href="../images/minOrder1.jpg">map of connections</a>      

**/

class MinReorder {

    public int minReorder(int n, int[][] connections) {
                
        Map<Integer, Set<int[]>> graph = new HashMap<>();
        
        for(int[] con : connections){       
            
            Set<int[]> set1 = graph.getOrDefault(con[0],new HashSet());
            set1.add(con);
            graph.put(con[0], set1);
                        
            Set<int []> set2 = graph.getOrDefault(con[1],new HashSet());
            set2.add(con);
            graph.put(con[1], set2);  
            
            /*A better way to do this*/
            //graph.computeIfAbsent(con[0], k -> new HashSet<>()).add(con);
            //graph.computeIfAbsent(con[1], k -> new HashSet<>()).add(con);
 
            //System.out.println(graph);
        }
        
        int count = 0;
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        
        while(!queue.isEmpty()){
            
            int cur = queue.poll();                       
            
            if(!graph.containsKey(cur) || graph.get(cur).size() == 0) continue;
            
            Set<int []> set = graph.get(cur);
            
            for(int [] conn : set){
                            
                if(conn[0] == cur){
                    count++;
                    graph.get(conn[1]).remove(conn);
                    queue.offer(conn[1]);
                } else{
                    graph.get(conn[0]).remove(conn);
                    queue.offer(conn[0]);
                }                
            }            
        }           
        return count;
        
    }
}