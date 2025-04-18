package com.app.slidingwindow;

import java.util.HashMap;
import java.util.Map;

public class FruitsInBasket {


    // LC - 904

    // Sliding window - Trick problem. Removing leftmost fruit is good enough. Don't have to bother removing all instances of the fruit at index - left.
         
    public int totalFruit(int[] fruits) {


         // FruitId, Count
        Map<Integer, Integer> fMap = new HashMap<>();
        
        int left = 0, right = 0;   
       
        for(right = 0; right < fruits.length; right++){
            
            fMap.put(fruits[right], fMap.getOrDefault(fruits[right], 0) + 1);

            // Remove leftmost fruit if window size is > 2
            if(fMap.size() > 2){               
                                    
                 fMap.put(fruits[left], fMap.get(fruits[left]) - 1);

                 if(fMap.get(fruits[left]) == 0) fMap.remove(fruits[left]);
                
                left++;
            }            
        }    
                
        return right - left;      

    }


    // Brute force (Time Limit Exceeded)
    /*public int totalFruits(int[] fruits) {
        
        int maxCount = 0;

        for(int i = 0 ; i < fruits.length; i++){
            
            Set<Integer> fruitIds = new HashSet<>();

            int countAtIndex = 0;    

            for(int j = i; j < fruits.length; j++){

                fruitIds.add(fruits[j]); // Add fruit to maintain unique count
                
                if(fruitIds.size() > 2) break;

                countAtIndex++;
            }

            maxCount = Math.max(maxCount,countAtIndex);

        }

        return maxCount;

    }*/

}
