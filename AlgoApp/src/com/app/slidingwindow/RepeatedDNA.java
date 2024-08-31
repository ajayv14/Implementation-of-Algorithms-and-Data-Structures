package com.app.slidingwindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNA {


    // LC - 187

    // Follow up to 438 all anagrams

    /*  
        Create a String Builder  to keep track of traversed substring

        Create a Set to store unique substrings of desired length of sliding window.

        Use two pointers. Move right pointer to increase length of the sliding window.

        Move left pointer whenever window size > length required.

        When window size == length required, check Set for uniqueness and string to result.
    
     */

    public List<String> findRepeatedDnaSequences(String s) {

        StringBuilder sb = new StringBuilder();

        List<String> dnas = new ArrayList<>();

        Set<String> subStrings = new HashSet<>();

        
        final int len = 10;

        int left = 0, right = 0, windowSize = 0;

        while(right < s.length()){
            
            sb.append(s.charAt(right));

            windowSize++;
         

            if(windowSize == 10 ) {            

               System.out.println(sb.toString());          

               if(subStrings.contains(sb.toString())) {

                    if(!dnas.contains(sb.toString())) dnas.add(sb.toString()); // avoid adding repeating substrings multiple times.
               }

                else subStrings.add(sb.toString());

                sb.deleteCharAt(0);
                left++;

                windowSize--;
            }            

            right++;
        }    

          return dnas;      
        
    }

}
