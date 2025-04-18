package com.app.strings;

import java.util.HashMap;
import java.util.Map;

public class FindValidPair {


    public String findValidPair(String s) {
        
        if(s == null || s.length() == 1) return s;

        Map<Character, Integer> freq = new HashMap<>();
            
        for(int i = 0; i < s.length(); i++){

            char c = s.charAt(i);

            freq.put(c, freq.getOrDefault(c, 0) + 1);                   

        }

        StringBuilder sb = new StringBuilder();

        for(int i = 1; i < s.length(); i++){

            char first = s.charAt(i - 1);
            char second = s.charAt(i);

            // Adjacent and not first not equal to second 
            if(first != second){
                
                if(freq.get(first) == first - '0' && freq.get(second) == second - '0'){
                     sb.append(first).append(second).toString();
                     break;
                }
            }         

        }

        return sb.toString();
    }
}
