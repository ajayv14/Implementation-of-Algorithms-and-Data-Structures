package com;

// LC 670. Maximum Swap https://leetcode.com/problems/maximum-swap

public class MaxSwap {

    // Can perform one swap only. Here approach is to find the bet pair to swap.
    // Note: When a number is found to be greater than maxSeenSofar, swap indices aren;t updated at that step
    
    public int maximumSwap(int num) {

        char[] nums = Integer.toString(num).toCharArray();

        int maxSeenSoFar = - 1; // store index only

        int swapIndex1 = -1, swapIndex2 = -1;

        for(int i = nums.length - 1; i >= 0 ; i--){
            
            if(maxSeenSoFar == -1 || nums[i] > nums[maxSeenSoFar]) maxSeenSoFar = i;

            else if(nums[i] < nums[maxSeenSoFar]){
                
                // update potential swap index
                swapIndex1 = i;
                swapIndex2 = maxSeenSoFar;
            }            
        }

        if(swapIndex1 > -1 && swapIndex2 > -1)  swap(nums, swapIndex1, swapIndex2);

        return Integer.parseInt(new String(nums));
        
    }


    private void swap(char[] nums, int i, int j){

        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
