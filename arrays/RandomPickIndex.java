package com.app.arrays;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LC : 398 https://leetcode.com/problems/random-pick-index/

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
