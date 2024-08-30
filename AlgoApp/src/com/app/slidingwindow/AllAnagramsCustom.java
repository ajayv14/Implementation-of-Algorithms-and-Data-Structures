package com.app.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//LC 438
// 63 out of 65 LC test cases passed
public class AllAnagramsCustom {



    public List<Integer> findAnagrams(String s, String p) {
                    
        
        Map<Character,Integer> freq = new HashMap<>();

        Map<Character,Integer> counter = new HashMap<>();
        
        List<Integer> list = new ArrayList<>();
        
        if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
                
        for(char c : p.toCharArray()){
            
            freq.put(c, freq.getOrDefault(c, 0) + 1);
            
        }

        System.out.println(freq);
     
        int left = 0, right = 0, wSize = 0; 
        
        while(right < s.length()){ 

           
            char c1 = s.charAt(right);
            
            counter.put(c1, counter.getOrDefault(c1,0) + 1);  

            ++wSize;            
         

            if(wSize == p.length()){    

                System.out.println("wSize : " + wSize);            

                boolean match = true; 
                          

                for(Map.Entry<Character,Integer> entry : freq.entrySet()){
                 
                    if(!counter.containsKey(entry.getKey()) || counter.get(entry.getKey()) != entry.getValue()){
                        match = false;
                        break;
                    }

                }

                System.out.println(match);

                if(match == true) list.add(left);     
 

                char c2 =  s.charAt(left);         
                
                counter.put(c2, counter.get(c2) - 1);

                if(counter.get(c2) == 0) counter.remove(c2);                

                left++; 
                --wSize;
            }  
          
            right++;                        
                                   
            
        }
        
        return list;
        
    }

}
