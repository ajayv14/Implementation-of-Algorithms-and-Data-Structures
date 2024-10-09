package com.app.dynamicprogramming;

class MaximalSquare {
     
   
        // LC 221
        // https://leetcode.com/problems/maximal-square
        
        /*DP non optimized-- https://www.youtube.com/watch?v=_Lf1looyJMU Thushar Roy

        
         Runtime O(m * n) :
         Space : O(m * n)
         */      
        
         public int maximalSquare(char[][] matrix) {             
        
            int max = 0;
    
            int[][] dp = new int[matrix.length][matrix[0].length];
    
            for(int row = 0; row < matrix.length; row++){
                for(int col = 0; col < matrix[0].length; col++){
    
                    if(matrix[row][col] == '0') continue; // skip           
                     
                    // Cells that have left, right and diagonal cell
                    if(row > 0 && col > 0){
                           
                        // 1 + min(diagonal, min(left and top))    
                        dp[row][col] = 1 + Math.min(dp[row - 1][col - 1], Math.min(dp[row - 1][col], dp[row][col - 1]));       
    
                    }
    
                    // Cells at left or top wall
                    else if(row == 0 || col == 0){
                        dp[row][col] = 1;
                    }
    
                    max = Math.max(max, dp[row][col]);
                }
            }      
    
    
            return max * max;  
            
        }  
   
}