package com.app.binarysearch;

// LC 35 - https://leetcode.com/problems/search-insert-position/description/

public class SearchInsert {


    // Binary search - minimization problem as we find the first spot where this element can be inserted.    
    public int searchInsert(int[] nums, int target) {

        int low = -1 ; 
        int high = nums.length;

        while(low + 1 < high){

            int mid = low + (high - low) / 2;

            //System.out.println(mid);
            
            if (nums[mid] >= target){
                high = mid;
            } 

            else low = mid;
        }
        
        return high;
    }

}
