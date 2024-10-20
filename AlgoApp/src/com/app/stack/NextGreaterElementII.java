package com.app.stack;

import java.util.*;

// LC 503 : https://leetcode.com/problems/next-greater-element-ii/

// Monotonic stack 
public class NextGreaterElementII {


    public int[] nextGreaterElements(int[] nums) {

        // Circular array, use % length of array

        int[] res = new int[nums.length];
        int N = nums.length;
        Arrays.fill(res, -1);


        Stack<Integer> stack = new Stack<>();

        //  nums.length * 2 to cover circular array scenario :
        //input [1, 2, 3, 4, 5]
        //i = 0-4: Normal case
        //i = 5-8: Circular case
        for(int i = 0; i < nums.length * 2; i++){

            while(!stack.isEmpty() && nums[stack.peek()] < nums[i % N]){

                res[stack.pop()] = nums[i % N];
            }

            stack.push(i % N);

        }


        return res;        
    }

}
