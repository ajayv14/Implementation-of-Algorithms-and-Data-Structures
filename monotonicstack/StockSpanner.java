package com.app.monotonicstack;

import java.util.Stack;


// LC 901 https://leetcode.com/problems/online-stock-span/

// Time : O(1) amortized
// Space : O(N)

public class StockSpanner {

    Stack<int[]> stack; //int[] contains price at 0 and span at index 1

    public StockSpanner() {
        stack = new Stack<>();        
    }
    
    // Span can either be 1 (previous price is higher than current) or 1 + span of previous day price. 
    public int next(int price) {
        
        int span = 1; //1 day

        while(!stack.isEmpty() && price >= stack.peek()[0]){

            span += stack.pop()[1];  //Get pre-computed span and add 1 day to it.
        }
        
        stack.push(new int[] {price, span});

        return stack.peek()[1]; // last element in stack has precoumputed span 
    }
}
