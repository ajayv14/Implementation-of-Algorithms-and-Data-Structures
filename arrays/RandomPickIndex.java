package com.app.arrays;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

// LC : 398 https://leetcode.com/problems/random-pick-index/


/**
    Optimized Approach : 

    Reservoir sampling : 
    
    

    {1, 2, 3, 3, 3}

    When you see the first target (count = 1), rand.nextInt(1) always returns 0 → you must pick it.

    For the second target (count = 2), rand.nextInt(2) gives 0 or 1 → 50% chance to pick the new one.

    For the third target (count = 3), 1/3 chance to pick it, 2/3 chance to keep the old one.
        
    And so on.

 */

// Time O(N) Space O(1)

class RandomPickIndexOptimized {

      int samples[];  
      Random rand;  

      public RandomPickIndexOptimized(int[] nums) {

        samples = nums;
        rand = new Random();

    }
    
    public int pick(int target) {
        
        int count = 0;
        int idx = 0;


        for(int i = 0; i < samples.length; i++){

            if(samples[i] == target){

                count++;

                if(rand.nextInt(count) ==  0){
                    idx = i;
                }
            }
        }


        return idx;        
        
    }
}




public class RandomPickIndex {

      
    // Non optimized : 

    // Create a map with target - list of occurences index.
    // For each target, pick one index from list randomly        

    Map<Integer, List<Integer>> occur = new HashMap<>();    

    public RandomPickIndex(int[] nums) {

        for(int i = 0; i < nums.length; i++){

            occur.putIfAbsent(nums[i], new ArrayList<>());
            occur.get(nums[i]).add(i);
        }
        
    }
    
    public int pick(int target) {
        
        List<Integer> numIdxList = occur.get(target);
        
        int genRandomIdx = (int) (Math.random() * numIdxList.size()); // Random num between 0 and list.size excluded.

        return numIdxList.get(genRandomIdx);        
        
    }

}
