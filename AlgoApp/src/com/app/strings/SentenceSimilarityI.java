package com.app.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// https://leetcode.com/problems/sentence-similarity/

public class SentenceSimilarityI {

    // Use Union find instead ?? 
    public boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {

        // Need to use Set<String> as each key may contain more than one similar word as value
        Map<String, Set<String>> simMap = new HashMap<>();        

        if(sentence1.length != sentence2.length) return false;

        for(List<String> sims : similarPairs){

            String p1 = sims.get(0); 
            String p2 = sims.get(1);

            simMap.computeIfAbsent(p1, k -> new HashSet<>());
            simMap.computeIfAbsent(p2, k -> new HashSet<>());

            simMap.get(p1).add(p2);
            simMap.get(p2).add(p1);
        }

        for(int i = 0; i < sentence1.length; i++){

            String w1 = sentence1[i]; 
            String w2 = sentence2[i];

            if(!w1.equals(w2)){
                
                if(!simMap.getOrDefault(w1, new HashSet()).contains(w2) && !simMap.getOrDefault(w2, new HashSet()).contains(w1)){

                    //System.out.println("w1 : " + w1 + " w2 : " + w2);    

                    return false; 
                }                  
            }
        }

        return true;
    }

}
