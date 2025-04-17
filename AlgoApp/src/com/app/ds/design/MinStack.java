package com.app.ds.design;


import java.util.Stack;


// Qns constraints : all operations in constant time. pop() top() and min() won't be called on empty stack

class MinStack {  
    
    
    // int[] contains a pair - value and min 
    Stack<int[]> stack;
   
    public MinStack() {
        stack = new Stack<>();          
    }
    
    public void push(int val) {

        if(stack.isEmpty()){

            stack.push(new int[] {val, val});
        }

        else {

            int min = stack.peek()[1];

            min = Math.min(min, val); 
       
            stack.push(new int[] {val, min});
        }    
    }
    
    public void pop() {
        
        stack.pop();   
    }
    
    public int top() {
        return stack.peek()[0];
    }
    
    public int getMin() {
        return stack.peek()[1];
    }
    
}
