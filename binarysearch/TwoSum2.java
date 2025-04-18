package com.app.binarysearch;


// LC 167 https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/

public class TwoSum2 {


    public int[] twoSum(int[] numbers, int target) {

        int low = 0, high = numbers.length - 1;

        while(low < high){

            int sum = numbers[low] + numbers[high];

            // + 1 is due to starting index should be 1 as per question
            if(sum == target) return new int[] { low + 1, high + 1}; 

            else if(sum < target) low++;

            else high--;
        }

        return new int[] {-1, -1};        
    }
}
