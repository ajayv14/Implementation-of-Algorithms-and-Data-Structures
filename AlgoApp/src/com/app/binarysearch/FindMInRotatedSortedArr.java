package com.app.binarysearch;


// LC 153 Find Minimum in Rotated Sorted Array

public class FindMInRotatedSortedArr {

    // Using minimization template


    public int findMin(int[] nums) {

        
        int low = -1, high = nums.length - 1;

        while(low + 1 < high){

            int mid = low + (high - low)/2;    

            if(nums[mid] <= nums[nums.length - 1]) high = mid;

            else low = mid; 

        }

        return nums[high];
        
    }



}
