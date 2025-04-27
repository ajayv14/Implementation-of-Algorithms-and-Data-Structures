// LC 643 https://leetcode.com/problems/maximum-average-subarray-i

public class MaxAverageSubArray {

    public double findMaxAverage(int[] nums, int k) {

        double windowSum = 0; // Method returns double, hence need for precesion.
        
        double maxAvg = Integer.MIN_VALUE; // edge case input [-1] & k = 1
        
        double avg = 0;        

        // When window is smaller than k, expand.
        for(int i = 0; i < k; i++){
            windowSum += nums[i];
        }

        avg = windowSum/k;        
        maxAvg = Math.max(maxAvg, avg);

        
        for(int i = k; i < nums.length; i++){   
            
            // Slide = add new value and remove leftmost value
            windowSum += nums[i] - nums[i - k]; 

            avg = windowSum/k;
            maxAvg = Math.max(maxAvg, avg);           
                 
        }

        return (double)maxAvg;        
    }

}
