package com.app.matrix;

public class MergeSortedArray {


    // LC 88. Merge Sorted Array
    // https://leetcode.com/problems/merge-sorted-array/

    // Time O(m + n) Space O(1)
    public void merge(int[] nums1, int m, int[] nums2, int n) {

        /*
            Merge from end of array in descending order.
         */
        
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;


        while(i >= 0 && j >= 0){
            
            if(nums1[i] > nums2[j]){
                nums1[k] = nums1[i];
                i--;
            }

            else {
                nums1[k] = nums2[j];
                j--;
            }
            k--;
        }

        
        // while i >= 0 is not required as 

        while(j >= 0){
            nums1[k] = nums2[j];
            j--;
            k--;
        }        
       
    }

}
