import java.util.Stack;

public class MaxRectangle {

    // https://leetcode.com/problems/maximal-rectangle

    // 85. Maximal Rectangle

     // Monotonic stack based solution - Similar to ractangle area from historam
    // Approach : Create a histogram chart single dim array
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





     /*
     * Approach - Using monotonic stack
     * For each index, find the closest smaller element to the left and right.   
     * Monotonically decreasing stack is used to find left and right boundaries.  
     * Calculate the area between these two elements.  
     * 
     */

     /**
    [2,1,5,6,2,3]

    len : 1 leftB -1 rightB : 1
    len : 1 leftB 2 rightB : 4
    len : 2 leftB 1 rightB : 4
    len : 1 leftB 4 rightB : 6
    len : 4 leftB 1 rightB : 6
    len : 6 leftB -1 rightB : 6
  */
   
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
