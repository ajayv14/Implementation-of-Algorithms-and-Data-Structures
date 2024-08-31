package com.app.slidingwindow;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepeatedDNA {


    // LC - 187

    // Follow up to 438 all anagrams

    /**
        Two pointer - fixed size sliding window. Expand window using right ptr till length is 10.

        Get the substring of len 10 and check against a set. 

        Move left ptr by a step after each iteration, thereby always restricting the window size to 10.    
     */

     public List<String> findRepeatedDnaSequences(String s) {
       
        List<String> dnas = new ArrayList<>();
        
        Set<String> uniqueStr = new HashSet<>();

        int left = 0, count = 0, dnaLength = 10;


        for(int right = 0; right < s.length(); right++){

            count++;

            if(count >= 10){

                String sub = s.substring(left, right + 1);

                if(uniqueStr.contains(sub) && !dnas.contains(sub)){
                    dnas.add(sub);
                }   

                uniqueStr.add(sub);

                left++;
            }
        }

        return dnas;
    }       
        


}
