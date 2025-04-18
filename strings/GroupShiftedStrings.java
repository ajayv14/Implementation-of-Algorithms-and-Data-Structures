package com.app.strings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC : 249 : https://leetcode.com/problems/group-shifted-strings/



public class GroupShiftedStrings {

     /*
        Compute distance between each character in string. Find similary strings with same distance
        efg can be turned into abc in 4 shift operations : efg -> def -> cde -> bcd -> abc
        But always distance btw each character remains same : f - e = 1, g - f = 1, so represent efg : 1->1
        Similarly acf : c - a = 2, e - c = 3,  acf : 2->3. A silimar string wud be : bdg.

        Edge case -  ba left shift -> az
        
        but az : 26 - 1 = 25  and ba : 1 - 2 = -1; So handle -ve by adding 26 (circular array)

     */

    public List<List<String>> groupStrings(String[] strings) {

        List<List<String>> res = new ArrayList<>();

        // Something like an inverted index
        // <DistanceOfEach chars in string, Matching strings>    
        Map<String, List<String>> distanceMap = new HashMap<>();

        
        for(String word : strings ){

            String distKey = computeDistance(word);

            distanceMap.putIfAbsent(distKey, new ArrayList<>());
                
            distanceMap.get(distKey).add(word);        
        }

                System.out.println(distanceMap);

        distanceMap.keySet().forEach(k -> res.add(distanceMap.get(k)));

        return res;
                
    }

    private String computeDistance(String word){

        StringBuilder sb = new StringBuilder();
        
        for(int i = 1; i < word.length(); i++){

            int dist = word.charAt(i) - word.charAt(i - 1);

            if(dist < 0) dist = dist + 26; 
            
            sb.append(String.valueOf(dist)+"->");
        }

        return sb.toString();
    }

}
