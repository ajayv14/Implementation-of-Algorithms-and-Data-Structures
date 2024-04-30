package com.app.dynamicprogramming;

import java.util.Stack;

// LC 84
public class LargestRectangleHistogram {


     /*
     * Approach - 1. Find nearest smaller bars to the left and right of each histogram bar. 
     * Here we use a stack to store previous heights indorectly -  stack to store indices of histogram bars.
     * Once smaller num on left and right is computed. Store in array and calculate
     * area at each index and return max.
     * Time complexity : O(N)
     * Space complexity : O(N) - Iterate the same array 3 times. 
     */

    public int largestRectangleArea(int[] heights) {

        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();

        int[] leftArray = new int[heights.length];
        int[] rightArray = new int[heights.length];

        // Compute left smaller element index for all elements
        for (int i = 0; i < heights.length; i++) {

            if (stack.isEmpty()) {
                leftArray[i] = 0;
                stack.push(i);
            }

            else {

                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    stack.pop();
                }

                leftArray[i] = stack.isEmpty() ? 0 : stack.peek() + 1;
                stack.push(i);

            }

        }

        stack.clear();

        // Compute right smaller element index for all elements
        for (int i = heights.length - 1; i >= 0; i--) {

            if (stack.isEmpty()) {
                rightArray[i] = heights.length - 1;
                stack.push(i);
            }

            else {

                while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                    stack.pop();
                }

                rightArray[i] = stack.isEmpty() ? (heights.length - 1) : stack.peek() - 1;
                stack.push(i);
            }
        }

        for (int i = 0; i < heights.length; i++) {

            int width = (rightArray[i] - leftArray[i]) + 1;

            int area = heights[i] * width;

            maxArea = Math.max(maxArea, area);
        }

        return maxArea;
    }




     // Time limit Exceeded - Brute force approach
     public int largestRectangleAreaBrute(int[] heights) {

        //Brute force : At each index, find an element to left < current value. 
        //Again find an element to the right with value < current value. Calculate Area. 
        // Find index with largest area;

        int maxArea = -1;

        for(int index = 0; index < heights.length; index++){

            // Find element smaller than current on left:
            int left = index - 1;

            while(left >= 0){
                if(heights[left] < heights[index]){
                    left++;
                    break;
                } 
                else left--;
            }
            // Find element smaller than current on right:   
            int right = index + 1;
            
            while(right < heights.length){
                if(heights[right] < heights[index]){
                    right--;
                    break;
                } 
                else right++;
            }

            // Fix boundaries for edge case [2,4] where left = -1 and right = heights.length
            if(left < 0) left = 0;
            if(right >= heights.length) right = heights.length - 1;
                        
            int width = (right - left) + 1;

            int area = heights[index] * width;  

            maxArea = Math.max(maxArea, area); 

            //System.out.println("Max area : " + maxArea + " for index : " + index);        

        }
        return maxArea;        
    }



    public static void main(String[] args) {
        
        LargestRectangleHistogram obj = new LargestRectangleHistogram();

        int[] input1 = new int[]{2,1,5,6,2,3};
        System.out.println("Expected : 10");
        System.out.println("Actual : " + obj.largestRectangleArea(input1));


        int[] input2 = new int[]{2,4};
        System.out.println("Expected : 4");
        System.out.println("Actual : " + obj.largestRectangleArea(input2));

        int[] input3 = new int[]{8035,8035,8035,8035};
        System.out.println("Expected : 32140");
        System.out.println("Actual : " + obj.largestRectangleArea(input3));   

    }

}
