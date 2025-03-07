package com.app.monotonicstack;

import java.util.Arrays;


//LC 300. Longest Increasing Subsequence
// https://leetcode.com/problems/longest-increasing-subsequence

public class LongestIncreasingSubsequence {


    /*
      Base case : nums array - at index 0 will have subseq of length 1.
      Now starting from index 1, check all nums to left of it, can it be included in strictly increasing subsequence ??      
    
     */


    public int lengthOfLIS(int[] nums) {
        
        int[] dp = new int[nums.length];

        Arrays.fill(dp, 1); // Each number itself can be a sequence of length 1

        int max = 1;

        for(int i = 1; i < nums.length; i++){

            for(int j = 0; j < i; j++){

                // condition true for strictly ncreasing subsequence - left < right
                if(nums[j] < nums[i]) {

                    dp[i] = Math.max(dp[j] + 1, dp[i]); 
                }
                // else ignore, won't form a strictly increasing subsequence
                
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }

}
