package com.app.strings;

import java.util.HashMap;
import java.util.Map;


//LC 76 Hard

// https://leetcode.com/problems/minimum-window-substring/

public class MinWindowSubstring {


    public String minWindow(String s, String t) {
        if (t.length() > s.length()) {
            return "";
        }

        // Frequency of occurence map
        Map<Character, Integer> freq = new HashMap<Character, Integer>();

        // initialize map
        for (int i = 0; i < t.length(); i++) {
            freq.put(t.charAt(i), freq.getOrDefault(t.charAt(i), 0) + 1);
        }

        int count = 0, left = 0, right = 0, minWindow = Integer.MAX_VALUE;
        
        int start = 0;

       
        /*
        It enters a loop that moves the right pointer to the right. For each character it encounters, it decrements its 
               frequency in the freq map and increments count if the frequency becomes positive.
               Once count becomes equal to the length of string t, it means we have found a window that contains all characters of 
               t. 
               
               It then enters a inner loop that moves the left pointer to the right to shrink the window. For each character it
                encounters, it increments its frequency in the freq map and decrements count if the frequency becomes positive.
                
                If the length of the current window is less than minWindow, it updates minWindow and start.
       
        * */

        for( right = 0 ; right < s.length(); right++) {

            char cur = s.charAt(right);
            
            if (freq.containsKey(cur)){

                  

                if(freq.get(cur) > 0) count++;

            } 
                                    
            // Substrign found where all characters in String t are present.                                   
            while (count == t.length()) {

                if(minWindow > right - left + 1){

                    minWindow = right - left + 1;

                    start = left; // Begin to shrink window
                }

                char leftCur = s.charAt(left); 

                if(freq.containsKey(leftCur)){

                     freq.put(leftCur, freq.get(leftCur) + 1);
                      if(freq.get(leftCur) > 0) count--;    
                }                 

                left++;          
            
            }   


            if(freq.containsKey(cur)) freq.put(cur, freq.get(cur) - 1);                   
        }

        return minWindow == Integer.MAX_VALUE ? "" : s.substring(start, start + minWindow);
    }

}
