package com.app.binarysearch;

public class IsMajorityElement {

    // LC : https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/
    // 1150. Check If a Number Is Majority Element in a Sorted Array

    public boolean isMajorityElement(int[] nums, int target) {
        
        // find first occurence of target

        int low = - 1, high = nums.length;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(nums[mid] >= target){
                high = mid;
            }

            else low = mid;
        }
        
        //System.out.println("leftmost occurence of target : : " + high);
        int leftMost = high;
        // find right most occurence of target
          
        low = -1;
        high = nums.length;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(nums[mid] <= target){
                low = mid;
            }
            else high = mid;
        } 

        //System.out.println("rightmost occurence of target : " + low);
        int rightMost = low;

        int length = rightMost + 1 - leftMost;
        

        return length > (nums.length/2);
    }

}
