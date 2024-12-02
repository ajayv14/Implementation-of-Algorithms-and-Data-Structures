package com.app.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;


// LC 269. Alien Dictionary -  https://leetcode.com/problems/alien-dictionary/description/

public class AlienDictionaryHard {

     // Kahn's algo - topo sort
     public String alienOrder(String[] words) {

        StringBuilder res = new StringBuilder();

        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character,Integer> inDegree = new HashMap<>();

        // Construct graph
        for(String word : words){

            for(char c : word.toCharArray()){
                graph.putIfAbsent(c, new ArrayList<>());
                inDegree.putIfAbsent(c, 0);
            }
        }

        for(int i = 0; i < words.length - 1; i++){

            String w1 = words[i], w2 = words[i + 1];
        
            // Check if w2 is not a prefix of w1
            if(w1.length() > w2.length() && w1.startsWith(w2)) return "";

            // Capture the first non matching char    
            
            for(int j = 0; j < Math.min(w1.length(),w2.length()); j++){

                char c1 = w1.charAt(j), c2 = w2.charAt(j);

                if( c1 != c2){

                    graph.get(c1).add(c2);
                    inDegree.put(c2,inDegree.get(c2) + 1);
                    break; // stop at first non matching
                }   
            }        
        }   

         // Kahn's topo sort   

         Queue<Character> queue = new LinkedList<>();

         for(Character key : inDegree.keySet()){

            if(inDegree.get(key) == 0) queue.add(key);
         }   


        while(!queue.isEmpty()){

            char c = queue.remove();

            res.append(c);

            // Decrease num of incoming edge as the previous connection was removed.
            for(char con : graph.get(c)){

                inDegree.put(con, inDegree.get(con) - 1); 

                if(inDegree.get(con) == 0) queue.add(con);                       
            }

        }

        // res doesn;t contain all characters, hence cyclic dependency is found
        if(res.length() != inDegree.size()) return "";

        return res.toString();

        
    }

}
