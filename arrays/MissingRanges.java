package com.app.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


// LC 163 : https://leetcode.com/problems/missing-ranges/

public class MissingRanges {


    public List<List<Integer>> findMissingRanges(int[] nums, int lower, int upper) {

        List<List<Integer>> res = new ArrayList<>();    

        int i = 0;

        while(i < nums.length){

            if(nums[i] != lower){

                List<Integer> missing = new ArrayList<>();
           
                res.add(Arrays.asList(lower, nums[i] - 1));
                
                lower = nums[i];
            }
           
            i++;
            lower++;                       
        }

        if(lower <= upper){
               
            res.add(Arrays.asList(lower, upper));
        }

        return res;
        
    }
}
