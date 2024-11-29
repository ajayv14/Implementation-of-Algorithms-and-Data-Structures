package com.app.binarysearch;

public class FindPeakElement {


    public int findPeakElement(int[] nums) {

        int low = -1, high = nums.length - 1;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(isGreaterThan(mid, nums)){
                high = mid;
            }

            else low = mid;
        }

        return high;        
    }

    private boolean isGreaterThan(int mid, int[] nums){

       // if(mid == nums.length - 1) return true;

        return nums[mid] > nums[mid + 1];
    }

    // Minimization problem

}
