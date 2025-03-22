package com.app.monotonicstack;

import java.util.Stack;


// https://leetcode.com/problems/maximum-width-ramp/
// LC 962. Maximum Width Ramp


public class MaxWidthRamp {

    //Time O(n) Space O(n)
    public int maxWidthRamp(int[] nums) {

        int max = 0;

        Stack<Integer> stack = new Stack<>();

        // Find NGE, so monotocally striclty decreasing
        for(int i = 0; i < nums.length; i++){

            if(stack.isEmpty() || nums[i] < nums[stack.peek()]){
                stack.push(i);
            }            
        }

        //System.out.println(stack);

        
        for(int j = nums.length - 1; j >= 0; j-- ){

            // If loop can pick only the first element in stack with i < j, nums[i] < nums[j],. but there cud be more
            while(!stack.empty() && nums[stack.peek()] <= nums[j]){
                
                max = Math.max(max, j - stack.pop());
            }
        }

        return max;   

        
    }
}
