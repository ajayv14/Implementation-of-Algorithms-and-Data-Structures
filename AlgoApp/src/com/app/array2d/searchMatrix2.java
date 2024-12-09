package com.app.array2d;

public class searchMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
     
        /*We start search the matrix from top right corner, initialize the current position to top right corner, if the target is greater than the value in current position, then the target can not be in entire row of current position because the row is sorted, if the target is less than the value in current position, then the target can not in the entire column because the column is sorted too. We can rule out one row or one column each time, so the time complexity is O(m+n). --- Comment Copied */ 
          
        
        if(matrix == null || matrix.length<1 || matrix[0].length<1 ) return false; // pay attention to sequence, null, row <1 and then col<1
        
        int row=0;
        int col = matrix[0].length-1;
        
        
         while(col >= 0 && row <= matrix.length-1){
             
             if(target == matrix[row][col]) return true; //top-right corner
             
             else if(target < matrix[row][col]) col--; // lower values are to the left of present cell.
             
             else if(target > matrix[row][col]) row++; // as array  is sorted both row and col wise, the higher values are found below in next row
                                
           
         }        
        
        return false;       
        
    }
}