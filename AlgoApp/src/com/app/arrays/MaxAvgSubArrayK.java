package com.app.arrays;

public class MaxAvgSubArrayK {

    // LC 643 
    public double findMaxAverage(int[] nums, int k) {

        double windowSum = 0; // Method returns double, hence need for precesion.
        double maxAvg = Integer.MIN_VALUE; // edge case input [-1] & k = 1
        double curAvg = 0;        

        // When window is smaller than k, expand.
        for(int i = 0; i < k; i++){
            windowSum += nums[i];
        }

        curAvg = windowSum/k;        
        maxAvg = Math.max(maxAvg, curAvg);

        int right = k; // Pointing to right end of window
        
        while(right < nums.length){   
            
            // Slide = add new value and remove leftmost value
            windowSum += nums[right] - nums[right - k]; 

            curAvg = windowSum/k;
            maxAvg = Math.max(maxAvg, curAvg);            
            right++;            
        }

        return (double)maxAvg;        
    }


    public static void main(String[] args){

        MaxAvgSubArrayK obj = new MaxAvgSubArrayK();

        double res1 = obj.findMaxAverage(new int[]{1,12,-5,-6,50,3}, 4);

        double res2 = obj.findMaxAverage(new int[]{5}, 1);

        double res3 = obj.findMaxAverage(new int[]{-1}, 1);

        System.out.println("Expected  : 12.75000");
        System.out.println("Actual : " + res1);

        System.out.println("Expected  : 5.00000");
        System.out.println("Actual : " + res2);

        System.out.println("Expected  : -1.00000");
        System.out.println("Actual : " + res3);
    }

}
