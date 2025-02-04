package com.app.array;

//credits : https://www.youtube.com/watch?v=jnoVtCKECmQ 

// Kadane's Algorithm

// LC   53. Maximum Subarray https://leetcode.com/problems/maximum-subarray/submissions/1531166940/

class MaxSubArray {
    

    // One pass Kadane's algo
    // Time : O(n), Space O(1)
    public int maxSubArray(int[] nums) {

        //Kadane's algo

        int maxSoFar = Integer.MIN_VALUE;
        int maxEndingHere = 0;

        for(int num : nums){

            maxEndingHere += num;

            maxSoFar = Math.max(maxSoFar, maxEndingHere);

            if(maxEndingHere < 0) maxEndingHere = 0; //reset

        }

        return maxSoFar;
        
    }

}
