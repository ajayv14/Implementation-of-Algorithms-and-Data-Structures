package com.app.slidingwindow;

// LC 1004
public class MaxConsequtiveOnesIII {


    // Sliding window - variable length

    public int longestOnes(int[] nums, int k) {

        int left = 0, right = 0, zeroCount = 0, maxCount = 0;    


        for(right = 0; right < nums.length; right++){

            if(nums[right] == 0) zeroCount++;


            // Move left pointer until we find a zero.
            while(zeroCount > k) {                
                            
                if(nums[left] == 0) zeroCount--;
                 
                left++;
            }

            // Window size is now optimal with just k number of zeros or less
            maxCount = Math.max(maxCount, (right + 1 - left));                  

        }
    
        return maxCount;   

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
