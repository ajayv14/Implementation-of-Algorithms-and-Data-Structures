package com.app.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class TownJudge {
    
    //crude logic - a judge needs to be trusted by N - 1 members.
    // and judge won't trust anyone
    
    public int findJudge(int N, int[][] trust) {
        
        if(N == 1) return 1;
                
        // <person, who all trust him>
        Map<Integer,Integer> trustMap = new HashMap<>();
        Set<Integer> trustsSomeone = new HashSet();    
        
        
        for(int[] pair : trust ){                
            trustMap.put(pair[1], trustMap.getOrDefault(pair[1],0) + 1);
            trustsSomeone.add(pair[0]);
        }
        
        for(Map.Entry<Integer,Integer> e : trustMap.entrySet()){
            
            if(e.getValue() == N - 1 && !trustsSomeone.contains(e.getKey())) return e.getKey();            
        }
        
        return -1;
    }
}