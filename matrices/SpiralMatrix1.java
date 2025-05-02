/*54. Spiral Matrix*/
package com.app.matrices;

import java.util.List;
import java.util.ArrayList;

// LC 54 : https://leetcode.com/problems/spiral-matrix/

public class SpiralMatrix1 {

    public List<Integer> spiralOrder(int[][] matrix) {

        List<Integer> res = new ArrayList<>();     

        int row = 0, col = 0, rowEnd = matrix.length - 1, colEnd = matrix[0].length - 1;

        
        while(row <= rowEnd && col <= colEnd){

            // Traverse from left to right
            for(int c = col ; c <= colEnd; c++){
                res.add(matrix[row][c]);            
            }

            row++; // Prevent reading the last cell in row while traversing downwards (top right)

            // Traverse from top to bottom
            for(int r = row; r <= rowEnd; r++){
                res.add(matrix[r][colEnd]);                
            } 

            colEnd--; // Prevent reading the last cell in col while traversing row (bottom right)          

            // Traverse from right to left
            
            if(row <= rowEnd){
                for(int c = colEnd; c >= col; c--){
                    res.add(matrix[rowEnd][c]);      
                }               
            }            
            rowEnd--;

            if(col <= colEnd){

                // Traverse from bottom to top    
                for(int r = rowEnd; r >= row; r--){
                    res.add(matrix[r][col]);    
                } 
            }             

            col++;
        }
        
        return res;
        
    }
    
    
    
    
    /*Test*/
    public static void main(String[] args){
      
         int[][] arr = {{1,2,3},     // expected o/p --> 1,2,3,6,9,8,7,4,5
                    {4,5,6},
                    {7,8,9}};
                    
                    
         SpiralMatrix1 obj = new SpiralMatrix1();
         
         List<Integer> res = obj.spiralOrder(arr);            
         
         System.out.println(res);           
      
    }
    
}