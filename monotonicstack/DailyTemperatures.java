package com.app.monotonicstack;

import java.util.Stack;

public class DailyTemperatures {



    // Monotonic stack 
    public int[] dailyTemperatures(int[] temperatures) {

        int[] res = new int[temperatures.length];

        Stack<Integer> stack = new Stack<>();


        for(int i = 0; i < temperatures.length; i++){

            while(!stack.isEmpty() && temperatures[stack.peek()] < temperatures[i]){

                int idx = stack.pop();

                res[idx] = i - idx;
            }

            stack.push(i);

        }

        return res;
        
    }

}
