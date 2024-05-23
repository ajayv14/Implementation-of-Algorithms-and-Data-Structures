package com.app.strings;

import java.util.HashSet;
import java.util.Set;

class LongestSubstringNonRepeating {


    // LC 3

    public int lengthOfLongestSubstring(String s) {
        
        Set<Character> set = new HashSet<>();

        int left = 0 ; // Sliding window

        int maxCount = 0;    
        int count = 0;
        // At each character
        for(int right = 0; right < s.length(); right++){

            char key = s.charAt(right);

            while(set.contains(key)){
                set.remove(s.charAt(left));
                left++;
                count --;
            }            

            set.add(key);          

            maxCount = Math.max(maxCount, ++count);

        }    

        return maxCount;    
    }
        
        
    //    "abcabcbb" , expected : 3
    //  "dvdf" , 3
    // "bbbbb", 1
}