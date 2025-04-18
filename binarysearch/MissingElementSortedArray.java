package com.app.binarysearch;

// LC 1060 Missing Element in Sorted Array https://leetcode.com/problems/missing-element-in-sorted-array

public class MissingElementSortedArray {


    // Using binary search ?? 
   // public int missingElement(int[] nums, int k) {

         


   // }


    public int missingElement2(int[] nums, int k) {
        

        for(int i = 1; i < nums.length; i++){

              // Example [1,4].Missing numbers are 2 and 3.
              // (4 - 1 - 1) = 2 -> count of missing numbers    
            int countOfMissingNumbers = nums[i] - nums[i - 1] - 1;

            if(countOfMissingNumbers < k) k-= countOfMissingNumbers;

            // countOfMissingNumbers >= k 
            else {

                return nums[i - 1] + k; // Simple logic - [1,4], k = 0, at 4, then 1 + 2 = 3.
            }        
        }

        return nums[nums.length - 1] + k;


    }

}
