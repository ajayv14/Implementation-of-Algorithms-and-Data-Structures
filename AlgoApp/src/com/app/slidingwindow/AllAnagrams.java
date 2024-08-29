package com.app.slidingwindow;

import java.util.ArrayList;
import java.util.List;

class AllAnagrams {


    /** 
          * make a frequency map of characters in str p
          * create a window start, end at 0 and expand till it reaches length of p
          * while expanding check if each char in str s is present in freq. array. Decrement it 
          * Decrement the count if char matches with the freq array and is >= 1
          * match is found when count == 0
          * if not, expand the window further by pushing start to the right and bump up the freq arr for each car. 
          * If char is part of p, then bump the count as well.       
        **/

    public List<Integer> findAnagrams(String s, String p) {       
      
        
        int[] map = new int[256];
        List<Integer> list = new ArrayList<>();
        
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
        
        
        for(char c : p.toCharArray()){
            map[c]++;
        }
        
        int start = 0,end=0,count=p.length(); // count is set to max of p, start and end for creating a sliding window
        
        while(end<s.length()){ //outer loop which goes thro each character of String s
            
            //now both start and end are at 0
            
            if(map[s.charAt(end)] >=1) count--; // we have already filled map array with values >=1 for characters present in string p 
               map[s.charAt(end)]--;//decreament as the char has been found in map array
               end++; // move end pointer to right
            
            //count == 0 then we have found a match
            if(count==0) list.add(start);            
            
            
            if(end-start== p.length()){ // time to move sliding window and length of p is reached
                
                if(map[s.charAt(start)]>=0){ // if present in p i.e map
                    count++;
                }
                
                map[s.charAt(start)]++;// bump the map as we removed it in last step 
                start++;
                
            }
            
            
        }
        
        return list;
        
    }
}