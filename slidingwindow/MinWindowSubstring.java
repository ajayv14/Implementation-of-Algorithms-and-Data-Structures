package com.app.slidingwindow;

import java.util.HashMap;
import java.util.Map;


//LC 76 Hard Minimum Window Substring

// https://leetcode.com/problems/minimum-window-substring/

public class MinWindowSubstring {


    public String minWindow(String s, String t) {

        if (t.length() > s.length()) return "";
        
        // Frequency of occurence map
        Map<Character, Integer> freq = new HashMap<Character, Integer>();

        // initialize map
        for (char c : t.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int matches = 0, left = 0, right = 0, minWindow = Integer.MAX_VALUE;
        
        int start = 0;
       

        for(right = 0 ; right < s.length(); right++) {

            char rightChar = s.charAt(right);
            
            //Decrement freq and increase count if a valid char is found
            if (freq.containsKey(rightChar)){
                
                freq.put(rightChar, freq.get(rightChar) - 1);

                if(freq.get(rightChar) >= 0) matches++;

            } 
                                    
            // Substring window found where all characters in String t are present. Try shrinking window now.  
            while (matches == t.length()) {

                if(minWindow > right - left + 1){

                    minWindow = right - left + 1;

                    start = left; // Begin to shrink window
                }

                char leftCur = s.charAt(left); 

                if(freq.containsKey(leftCur)){

                     freq.put(leftCur, freq.get(leftCur) + 1);
                     
                     if(freq.get(leftCur) > 0) matches--;    
                }                

                left++;          
            }              
        }

        return minWindow == Integer.MAX_VALUE ? "" : s.substring(start, start + minWindow);
    }



        /*
            Pre-populate map with character count in t.
            
            For each character encountered, if present in the freq map, decrement freq and increment matches count.

            Once count becomes equal to the length of string t, it means we have found a window that contains all characters of t. 
               
            Now try shrinking this window by moving left pointer to right. Mark current pos of left pointer using start. For each character it
            encounters, it increments its frequency in the freq map and decrements matches count if the frequency becomes positive.
              
            Shrink example : 

            s = "ADOBACODEBANC", t = "ABC"

            initial match - ADOBAC
            shrink match - BAC    
        * */
}
