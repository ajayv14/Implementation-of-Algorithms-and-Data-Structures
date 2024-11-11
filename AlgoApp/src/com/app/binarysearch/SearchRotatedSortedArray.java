package com.app.binarysearch;

public class SearchRotatedSortedArray {


    // Find min using minimization bin search template, then look for target in right half or left half.

    // Time limit exceeded.

    // LC 33 - https://leetcode.com/problems/search-in-rotated-sorted-array

    public int search(int[] nums, int target) {

        int minIndex = findMin(nums);

        //System.out.println("min idx : " + minIndex);
     
        if(target <= nums[nums.length - 1]) return binSearch(nums, target, minIndex, nums.length - 1);

        else return  binSearch(nums, target, 0, minIndex - 1);
        
    }


    private int findMin(int[] nums){

        int low = -1, high = nums.length - 1;

        while(low + 1 < high){

            int mid = low + (high - low)/2;

            if(nums[mid] <= nums[nums.length - 1]) high = mid;

            else low = mid;
        }

        return high;
    }

    private int binSearch(int[] nums, int target, int low, int high){

        while(low <= high){

            int mid = low + (high - low)/2;

            if(nums[mid] == target) return mid;

            else if(nums[mid] < target) low = mid - 1;

            else high = mid - 1;           

        }
        return -1;

    }

}
