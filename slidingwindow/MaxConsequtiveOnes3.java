package com.app.slidingwindow;

// LC 1004 https://leetcode.com/problems/max-consecutive-ones-iii

// Use sliding window with left and right pointers. Count zeros in window. 
// When zeros exceed k, shrink window from left until valid. Track max window length. Window represents flippable range.
     
public class MaxConsequtiveOnesIII {

// Sliding window - variable length

public int longestOnes(int[] nums, int k) {

    int left = 0, right = 0;
    int zeros = 0;
    int maxLen = 0;    

    while(right < nums.length){

        if(nums[right] == 0) zeros++;

        // Move left pointer until we find a zero.
        // Slide the window to right and decrement count of zeros if we encounter a zero, then left++
        while(zeros > k) {                
                        
            if(nums[left] == 0) zeros--;
             
            left++;
        }
        
        // Window size is now optimal with just k number of zeros or less
        maxLen = Math.max(maxLen, right - left + 1); // + 1 to compensate for index 0                  

        right++;

    }

    return maxLen;   

}




    /*
     * 
     *  Test cases :
     * 
     *  Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
        Output: 6
        
        Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
        Output: 10
     * 
     * [0,0,0,1]
     * 4
     * 
     * 
     * 
     * 
     * 
     */


}
