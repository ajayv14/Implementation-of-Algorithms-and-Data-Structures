
import java.util.*;

// LC - 84 https://leetcode.com/problems/largest-rectangle-in-histogram/

public class LargestRectangleHistogram {

     /*
     * Approach - Using monotonic stack
     * For each index, find the closest smaller element to the left and right.   
     * Monotonically decreasing stack is used to find left and right boundaries.  
     * Calculate the area between these two elements.  
     * 
     */
   
     public int largestRectangleArea(int[] heights) {

        int maxArea = 0;

        Stack<Integer> stack = new Stack<>();

        
        for (int i = 0; i <= heights.length; i++) {
                    

            while (!stack.isEmpty() && (i == heights.length || heights[stack.peek()] >= heights[i])) {
                    
                    int mid = stack.pop();
                    int leftBoundary = stack.isEmpty() ? -1 : stack.peek();
                    int rightBoundary = i;

                    int height = heights[mid];

                    // This ensures the rectangle spans from leftBoundary + 1 to rightBoundary - 1.
                    // Ignoring the smaller elements than current.
                    int len =  rightBoundary - leftBoundary - 1;

                    maxArea = Math.max(maxArea,  height * len );                   

            }
              
            if(i < heights.length) stack.push(i);            

        }
      

        return maxArea;
    }

}


/**
 * heights =[2,1,5,6,2,3] Output 10
 * [2,4] 4
 * [1,1] 2 
 */