package com.app.array;

// LC 962. Maximum Width Ramp
// https://leetcode.com/problems/maximum-width-ramp/

// TLE : 97 / 101 testcases passed

// Needs to be improved later

public class MaximumWidthRamp {

    public int maxWidthRamp(int[] nums) {

        int maxWidth = 0;

        int left = 0, right = nums.length - 1;
     

        while(left < nums.length){

            if(left < right && nums[left] <= nums[right]){

                maxWidth = Math.max(maxWidth, right - left);                   

                left++;
                right =  nums.length - 1;
            }

            else if(nums[left] > nums[right]){
                
                right--;
            }

            if(right == left){
                left++;
                right = nums.length - 1;
            }

        }
        
        return maxWidth;
    }      

}
