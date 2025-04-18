package com.app.monotonicstack;
import java.util.Stack;

public class MaxRectangle {

    // https://leetcode.com/problems/maximal-rectangle

    // 85. Maximal Rectangle

     // Monotonic stack based solution - Similar to ractangle area from historam

    public int maximalRectangle(char[][] matrix) {

        int maxArea = 0; 
        
        int[] heights = new int[matrix[0].length];

        for(int row = 0; row < matrix.length; row++){
            for(int col = 0; col < matrix[0].length; col++){
                
                heights[col] = matrix[row][col] == '1' ? heights[col] + 1 : 0;
            }

            int area = findMaxRectAreaOf1s(heights);
            maxArea = Math.max(maxArea,area);   
        }       
        return maxArea;
    }

    private int findMaxRectAreaOf1s(int[] heights){

       
        Stack<Integer> stack = new Stack<>();

        int maxArea = 0;
        

        for(int i = 0; i <= heights.length; i++){
                        
            while(!stack.isEmpty() && (i == heights.length || heights[stack.peek()] >= heights[i])){

                int mid = stack.pop();
                int leftBoundary = stack.isEmpty() ? -1 : stack.peek(); 
                int rightBoundary = i;

                int length = rightBoundary - leftBoundary - 1;
                int height = heights[mid];

                int area = length * height;
                maxArea = Math.max(maxArea, area);

            }

            if(i < heights.length) stack.push(i);
        }

        return maxArea;

    }    

}
